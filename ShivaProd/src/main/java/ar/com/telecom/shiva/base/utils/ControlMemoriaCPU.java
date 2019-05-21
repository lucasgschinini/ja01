package ar.com.telecom.shiva.base.utils;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class ControlMemoriaCPU {
	
	private static Runtime runtime = Runtime.getRuntime();
	
	
	public ControlMemoriaCPU () {
	}
	

	public static String getInformacionGeneralSistema() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constantes.RETORNO_UNIX);
        sb.append("********************************************************************");
        sb.append(Constantes.RETORNO_UNIX);
        sb.append(OsInfo());
        sb.append(Constantes.RETORNO_UNIX);
        sb.append(MemInfo());
        sb.append(Constantes.RETORNO_UNIX);
        sb.append("********************************************************************");
        return sb.toString();
    }

	public static String getInformacionEspacio() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constantes.RETORNO_UNIX);
        sb.append("********************************************************************");
        sb.append(Constantes.RETORNO_UNIX);
        sb.append(DiskInfo());
        sb.append(Constantes.RETORNO_UNIX);
        sb.append("********************************************************************");
        return sb.toString();
    }
	
	public static String getInformacionMemoria() {
        StringBuilder sb = new StringBuilder();
        sb.append(MemInfo());
        return sb.toString();
    }
	
	public static String getInformacionCPU(long prevUpTime, long prevProcessCpuTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(getUsoCpu(prevUpTime, prevProcessCpuTime));
        return sb.toString();
    }
	
	public static String getInformacionMemoriaCPU(long prevUpTime, long prevProcessCpuTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constantes.RETORNO_UNIX);
        sb.append("********************************************************************");
        sb.append(Constantes.RETORNO_UNIX);
        sb.append(MemInfo());
        sb.append(Constantes.RETORNO_UNIX);
        sb.append(getUsoCpu(prevUpTime, prevProcessCpuTime));
        sb.append(Constantes.RETORNO_UNIX);
        sb.append("********************************************************************");
        return sb.toString();
    }
	
	private static String MemInfo() {
        StringBuilder sb = new StringBuilder();
        long max = runtime.maxMemory();
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long used = total - free;

        DecimalFormat df = new DecimalFormat("0.00");
        sb.append("Memoria -->  ");
        sb.append("Ocupada: " + getSizeBytes(used) + "("+ df.format(((double)used/(double)total)*100) +" %)");
        sb.append(" / ");
        sb.append("Libre: " + getSizeBytes(free) + "("+ df.format(((double)free/(double)total)*100) +" %)");
        sb.append(" / ");
        sb.append("Total: " + getSizeBytes(total));
        sb.append(" / ");
        sb.append("Max: " + getSizeBytes(max));

        return sb.toString();

    }

    /**
     * Informacion del Sistema operativo
     * @return
     */
    private static String OsInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("OS: " + OSname() + " Version: " + OSversion() + " : " + OsArch());
        sb.append(Constantes.RETORNO_UNIX);
        sb.append("Available processors (cores): " + runtime.availableProcessors());
        return sb.toString();
    }

    /**
     * Informacion del disco
     * @return
     */
    private static String DiskInfo() {
        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();
        StringBuilder sb = new StringBuilder();

        /* For each filesystem root, print some info */
        for (File root : roots) {
            sb.append("File system root: ");
            sb.append(root.getAbsolutePath());
            sb.append(Constantes.RETORNO_UNIX);
            sb.append("Total space: " + getSizeBytes(root.getTotalSpace()) + " / "
            		+ "Free space: " +getSizeBytes(root.getFreeSpace()) + " / "
            		+ "Usable space: " +getSizeBytes(root.getUsableSpace()));
            sb.append(Constantes.RETORNO_UNIX);
        }
        return sb.toString();
    }
    /**
     * 
     * @param size
     * @return
     */
    public static String getSizeBytes(long size) {

        DecimalFormat df = new DecimalFormat("0.00");

        float sizeKb = 1024.0f;
        float sizeMb = sizeKb * sizeKb;
        float sizeGb = sizeMb * sizeKb;
        float sizeTerra = sizeGb * sizeKb;


        if(size < sizeMb)
            return df.format(size / sizeKb)+ " KB";
        else if(size < sizeGb)
            return df.format(size / sizeMb) + " MB";
        else if(size < sizeTerra)
            return df.format(size / sizeGb) + " GB";

        return "";
    }
    
    
    private static String OSname() {
        return System.getProperty("os.name");
    }

    private static String OSversion() {
        return System.getProperty("os.version");
    }

    private static String OsArch() {
        return System.getProperty("os.arch");
    }
    
    //Devuelve el cpu time
	public static long getProcessCpuTime() {
		try {
		  //MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();
		  //OperatingSystemMXBean osMBean = ManagementFactory.newPlatformMXBeanProxy(
		  //			mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
				
		  OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
          Method method1 = operatingSystemMXBean.getClass().getDeclaredMethod("getProcessCpuTime", null);
          method1.setAccessible(true);
          Object value1 = method1.invoke(operatingSystemMXBean, new Object[]{});
          return (long) value1;
    	 
          
	    } catch (Exception e1) {
	    	Traza.error(ControlMemoriaCPU.class, e1.getMessage(), e1);
	        return 0;
	    }
	}
	
	/**
	 * 
	 * @return
	 */
	private static int getAvailableProcessors() {
		try {
			OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
			return operatingSystemMXBean.getAvailableProcessors();
		} catch (Exception e1) {
			Traza.error(ControlMemoriaCPU.class, e1.getMessage(), e1);
			return 0;
		}
	}
	
	/**
	 * 
	 * @param availableProcessors
	 * @param prevUpTime
	 * @param prevProcessCpuTime
	 * @return
	 */
	private static String getUsoCpu(long prevUpTime, long prevProcessCpuTime) {
		double cpuUsage;
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		long upTime = runtimeMXBean.getUptime();
		long processCpuTime = getProcessCpuTime();
		long elapsedCpu = processCpuTime - prevProcessCpuTime;
		long elapsedTime = upTime - prevUpTime;
	
		cpuUsage = Math.min(99F, elapsedCpu / (elapsedTime * 10000F * getAvailableProcessors()));
		
		DecimalFormat df = new DecimalFormat("0.00");
		return "Java CPU: " + df.format(cpuUsage) + " %";
	}
	
	
	
}
  




