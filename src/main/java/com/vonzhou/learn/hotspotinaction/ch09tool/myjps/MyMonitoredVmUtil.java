package com.vonzhou.learn.hotspotinaction.ch09tool.myjps;

import java.util.List;

import sun.jvmstat.monitor.ByteArrayMonitor;
import sun.jvmstat.monitor.IntegerMonitor;
import sun.jvmstat.monitor.LongMonitor;
import sun.jvmstat.monitor.Monitor;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.StringMonitor;


public class MyMonitoredVmUtil {

	/**
	 * Private constructor - prevent instantiation.
	 */
	private MyMonitoredVmUtil() {
	}

	/**
	 * Return the Java Virtual Machine Version.
	 * 
	 * @param vm
	 *            the target MonitoredVm
	 * @return String - contains the version of the target JVM or the the string
	 *         "Unknown" if the version cannot be determined.
	 */
	public static String vmVersion(MonitoredVm vm) throws MonitorException {
		StringMonitor ver = (StringMonitor) vm.findByName("java.property.java.vm.version");
		return (ver == null) ? "Unknown" : ver.stringValue();
	}

	/**
	 * Return the command line for the target Java application.
	 * 
	 * @param vm
	 *            the target MonitoredVm
	 * @return String - contains the command line of the target Java application
	 *         or the the string "Unknown" if the command line cannot be
	 *         determined.
	 */
	public static String commandLine(MonitoredVm vm) throws MonitorException {
		StringMonitor cmd = (StringMonitor) vm.findByName("sun.rt.javaCommand");
		return (cmd == null) ? "Unknown" : cmd.stringValue();
	}

	/**
	 * Return the arguments to the main class for the target Java application.
	 * Returns the arguments to the main class. If the arguments can't be found,
	 * the string "Unknown" is returned.
	 * 
	 * @param vm
	 *            the target MonitoredVm
	 * @return String - contains the arguments to the main class for the target
	 *         Java application or the the string "Unknown" if the command line
	 *         cannot be determined.
	 */
	public static String mainArgs(MonitoredVm vm) throws MonitorException {
		String commandLine = commandLine(vm);

		int firstSpace = commandLine.indexOf(' ');
		if (firstSpace > 0) {
			return commandLine.substring(firstSpace + 1);
		} else if (commandLine.compareTo("Unknown") == 0) {
			return commandLine;
		} else {
			return null;
		}
	}

	/**
	 * Return the main class for the target Java application. Returns the main
	 * class or the name of the jar file if the application was started with the
	 * <em>-jar</em> option.
	 * 
	 * @param vm
	 *            the target MonitoredVm
	 * @param fullPath
	 *            include the full path to Jar file, where applicable
	 * @return String - contains the main class of the target Java application
	 *         or the the string "Unknown" if the command line cannot be
	 *         determined.
	 */
	public static String mainClass(MonitoredVm vm, boolean fullPath) throws MonitorException {
		String commandLine = commandLine(vm);
		String arg0 = commandLine;

		int firstSpace = commandLine.indexOf(' ');
		if (firstSpace > 0) {
			arg0 = commandLine.substring(0, firstSpace);
		}
		if (!fullPath) {
			/*
			 * can't use File.separator() here because the separator for the
			 * target jvm may be different than the separator for the monitoring
			 * jvm.
			 */
			int lastFileSeparator = arg0.lastIndexOf('/');
			if (lastFileSeparator > 0) {
				return arg0.substring(lastFileSeparator + 1);
			}

			lastFileSeparator = arg0.lastIndexOf('\\');
			if (lastFileSeparator > 0) {
				return arg0.substring(lastFileSeparator + 1);
			}

			int lastPackageSeparator = arg0.lastIndexOf('.');
			if (lastPackageSeparator > 0) {
				return arg0.substring(lastPackageSeparator + 1);
			}
		}
		return arg0;
	}

	/**
	 * Return the JVM arguments for the target Java application.
	 * 
	 * @param vm
	 *            the target MonitoredVm
	 * @return String - contains the arguments passed to the JVM for the target
	 *         Java application or the the string "Unknown" if the command line
	 *         cannot be determined.
	 */
	public static String jvmArgs(MonitoredVm vm) throws MonitorException {
		StringMonitor jvmArgs = (StringMonitor) vm.findByName("java.rt.vmArgs");
		return (jvmArgs == null) ? "Unknown" : jvmArgs.stringValue();
	}

	/**
	 * Return the JVM flags for the target Java application.
	 * 
	 * @param vm
	 *            the target MonitoredVm
	 * @return String - contains the flags passed to the JVM for the target Java
	 *         application or the the string "Unknown" if the command line
	 *         cannot be determined.
	 */
	public static String jvmFlags(MonitoredVm vm) throws MonitorException {
		StringMonitor jvmFlags = (StringMonitor) vm.findByName("java.rt.vmFlags");
		return (jvmFlags == null) ? "Unknown" : jvmFlags.stringValue();
	}

	// Index of the sun.rt.jvmCapabilities counter
	private static int IS_ATTACHABLE = 0;
	private static int IS_KERNEL_VM = 1;

	/**
	 * ���ָ�������֧�ֵ����м�������Monitor:StringMonitor��
	 * 
	 * @param vm
	 *            the target MonitoredVm
	 */
	public static List listMonitors(MonitoredVm vm) throws MonitorException {
		List<Monitor> listMonitors = vm.findByPattern(".+");
		for (int i = 0; i < listMonitors.size(); i++) {
			Monitor m = listMonitors.get(i);
			if (m instanceof StringMonitor) {
				System.out.println(((StringMonitor) m).getName() + ": " + ((StringMonitor) m).stringValue());
			} else if (m instanceof LongMonitor) {
				System.out.println(((LongMonitor) m).getName() + ": " + ((LongMonitor) m).longValue());
			} else if (m instanceof IntegerMonitor) {
				System.out.println(((IntegerMonitor) m).getName() + ": " + ((IntegerMonitor) m).intValue());
			} else if (m instanceof ByteArrayMonitor) {
				System.out.println(((ByteArrayMonitor) m).getName() + ": " + ((ByteArrayMonitor) m).byteArrayValue());
			}

		}
		return listMonitors;
	}

	/**
	 * Returns true if the VM supports attach-on-demand.
	 * 
	 * @param vm
	 *            the target MonitoredVm
	 */
	public static boolean isAttachable(MonitoredVm vm) throws MonitorException {
		StringMonitor jvmCapabilities = (StringMonitor) vm.findByName("sun.rt.jvmCapabilities");
		if (jvmCapabilities == null) {
			return false;
		} else {
			return jvmCapabilities.stringValue().charAt(IS_ATTACHABLE) == '1';
		}
	}

	/**
	 * ��ö�̬���ӿ�·��
	 * 
	 * @param vm
	 * @return
	 * @throws MonitorException
	 */
	public static String libraryPath(MonitoredVm vm) throws MonitorException {
		StringMonitor libraryPath = (StringMonitor) vm.findByName("java.property.java.library.path");
		return (libraryPath == null) ? "Unknown" : libraryPath.stringValue();
	}

	/**
	 * 
	 * @param vm
	 * @return
	 * @throws MonitorException
	 */
	public static String capabilities(MonitoredVm vm) throws MonitorException {
		StringMonitor libraryPath = (StringMonitor) vm.findByName("sun.rt.jvmCapabilities");
		return (libraryPath == null) ? "Unknown" : libraryPath.stringValue();
	}

}
