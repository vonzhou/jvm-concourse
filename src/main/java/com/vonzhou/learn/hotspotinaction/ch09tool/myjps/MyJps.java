package com.vonzhou.learn.hotspotinaction.ch09tool.myjps;

import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import sun.jvmstat.monitor.HostIdentifier;
import sun.jvmstat.monitor.Monitor;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.StringMonitor;
import sun.jvmstat.monitor.VmIdentifier;

// 程序示例：列举java进程。注意：需要引入tools.jar。

public class MyJps {

	private static MyArguments myArguments;

	public static void main(String[] args) {
		try {
			args = new String[]{"-p"};
			myArguments = new MyArguments(args);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			MyArguments.printUsage(System.err);
			return;
		}

		if (myArguments.isHelp()) {
			MyArguments.printUsage(System.out);
			System.exit(0);
		}

		try {
			HostIdentifier hostId = myArguments.hostId();  // 默认是 //localhost
			MonitoredHost monitoredHost = MonitoredHost.getMonitoredHost(hostId);

			// 列出目标系统上所有活跃的JVM
			Set jvms = monitoredHost.activeVms();

			for (Iterator j = jvms.iterator(); j.hasNext(); /* empty */) {
				StringBuilder output = new StringBuilder();
				Throwable lastError = null;

				int lvmid = ((Integer) j.next()).intValue();

				output.append(String.valueOf(lvmid));

				if (myArguments.isQuiet()) {
					System.out.println(output);
					continue;
				}

				MonitoredVm vm = null;
				String vmidString = "//" + lvmid + "?mode=r";

				String errorString = null;

				try {
					// Note: The VM associated with the current VM id may
					// no longer be running so these queries may fail. We
					// already added the VM id to the output stream above.
					// If one of the queries fails, then we try to add a
					// reasonable message to indicate that the requested
					// info is not available.

					errorString = " -- process information unavailable";
					VmIdentifier id = new VmIdentifier(vmidString);
					vm = monitoredHost.getMonitoredVm(id, 0);

					errorString = " -- main class information unavailable";
					output.append(" " + MyMonitoredVmUtil.mainClass(vm, myArguments.showLongPaths()));

					if (myArguments.showMainArgs()) {
						errorString = " -- main args information unavailable";
						String mainArgs = MyMonitoredVmUtil.mainArgs(vm);
						if (mainArgs != null && mainArgs.length() > 0) {
							output.append(" " + mainArgs);
						}
					}
					if (myArguments.showVmArgs()) {
						errorString = " -- jvm args information unavailable";
						String jvmArgs = MyMonitoredVmUtil.jvmArgs(vm);
						if (jvmArgs != null && jvmArgs.length() > 0) {
							output.append(" " + jvmArgs);
						}
					}

					/**
					 * added here
					 */
					if (myArguments.showLibPath()) {
						errorString = " -- library path information unavailable";
						String jvmFlags = MyMonitoredVmUtil.libraryPath(vm);
						if (jvmFlags != null && jvmFlags.length() > 0) {
							output.append(" " + jvmFlags);
						}
					}
					if (myArguments.showJvmCapabilities()) {
						errorString = " -- Jvm Capabilities information unavailable";
						String jvmFlags = MyMonitoredVmUtil.capabilities(vm);
						if (jvmFlags != null && jvmFlags.length() > 0) {
							output.append(" " + jvmFlags);
						}
					}
					if (myArguments.listMonitors()) {
						errorString = " -- listMonitors information unavailable";
						List<Monitor> list = MyMonitoredVmUtil.listMonitors(vm);
						for (Monitor m : list) {
							StringMonitor s = (StringMonitor) m;

							output.append(" " + s.stringValue());
						}
					}

					errorString = " -- detach failed";
					monitoredHost.detach(vm);

					System.out.println(output);

					errorString = null;
				} catch (URISyntaxException e) {
					// unexpected as vmidString is based on a validated hostid
					lastError = e;
					assert false;
				} catch (Exception e) {
					lastError = e;
				} finally {
					if (errorString != null) {
						/*
						 * we ignore most exceptions, as there are race
						 * conditions where a JVM in 'jvms' may terminate before
						 * we get a chance to list its information. Other
						 * errors, such as access and I/O exceptions should stop
						 * us from iterating over the complete set.
						 */
						output.append(errorString);
						if (myArguments.isDebug()) {
							if ((lastError != null) && (lastError.getMessage() != null)) {
								output.append("\n\t");
								output.append(lastError.getMessage());
							}
						}
						System.out.println(output);
						if (myArguments.printStackTrace()) {
							lastError.printStackTrace();
						}
						continue;
					}
				}
			}
		} catch (MonitorException e) {
			if (e.getMessage() != null) {
				System.err.println(e.getMessage());
			} else {
				Throwable cause = e.getCause();
				if ((cause != null) && (cause.getMessage() != null)) {
					System.err.println(cause.getMessage());
				} else {
					e.printStackTrace();
				}
			}
		}
	}
}
