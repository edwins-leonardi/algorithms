package bitwise;


import edu.princeton.cs.algs4.StdOut;

/**
 * Given a start and an end ip, list all subnets needed to minimally cover all the IPS in the range
 */
public class MinimumSubnet {
    public static void main(String[] args) {
        System.out.println(getMinimumSubnetForIps("1.1.1.1", "1.1.1.1"));
        System.out.println(getMinimumSubnetForIps("1.1.1.1", "1.1.1.0"));
        System.out.println(getMinimumSubnetForIps("1.1.1.1", "1.1.1.2"));
        System.out.println(getMinimumSubnetForIps("100.64.96.0", "100.64.98.0"));
        System.out.println(getMinimumSubnetForIps("100.64.96.0", "100.64.96.64"));
        System.out.println(getMinimumSubnetForIps("100.64.96.0", "100.64.96.3"));
    }

    public static String getMinimumSubnetForIps(String firstIp, String secondIp) {
        long ip1 = convertIpStringToLong(firstIp);
        long ip2 = convertIpStringToLong(secondIp);
        long distance = Math.abs(ip2-ip1) + 1;
        long logBase2 = getLogBase2(distance);
        long subnetAddress1 = getSubnetAddressForIp(ip1, (int)logBase2);
        long subnetAddress2 = getSubnetAddressForIp(ip2, (int)logBase2);
        if (subnetAddress1 == subnetAddress2)
            return longToIp(subnetAddress1)+"/"+(32-logBase2);
        String subnet1 = longToIp(subnetAddress1)+"/"+(32-logBase2);
        String subnet2 = longToIp(subnetAddress2)+"/"+(32-logBase2);
        return subnet1 + "," + subnet2;
    }

    public static long convertIpStringToLong(String ip){
        long ipAsInt = 0;
        String[] octects = ip.split("\\.");
        for (int i = 0; i<octects.length; i++) {
            int octectAsInt = Integer.parseInt(octects[i]);
            ipAsInt += octectAsInt << (24 - (8 * i));
        }
        return ipAsInt;
    }

    public static String longToIp(long ip) {
        StringBuilder sb = new StringBuilder(15);

        for (int i = 0; i < 4; i++) {

            sb.insert(0,Long.toString(ip & 0xff));

            if (i < 3) {
                sb.insert(0,'.');
            }

            ip = ip >> 8;
        }
        return sb.toString();
    }

    public static long getSubnetAddressForIp(long ip, long logBase2){
        long subnetMask = (-1 << (logBase2));
        return ip & subnetMask;
    }

    public static long getLogBase2(long number){

        return (long)(Math.log(number) / Math.log(2));
    }
}
