package string;

public class ValidateIp {

    public boolean isValidIp(String ip) {
        String[] array = ip.split("\\.");

        if (array.length != 4) {
            return false;
        }

        for (String p: array) {
            if (!isValidNumber(p)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidNumber(String p) {
        try {
            int i = Integer.valueOf(p);
            return i >= 0 && i <= 255;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        ValidateIp validateIp = new ValidateIp();

        System.out.println(validateIp.isValidIp("14.8.9.28"));
        System.out.println(validateIp.isValidIp("255.255.255.255"));
        System.out.println(validateIp.isValidIp("1.0.0.9"));
        System.out.println(validateIp.isValidIp("0.0.0.0"));


        System.out.println(validateIp.isValidIp("100.xyz.1.15"));
        System.out.println(validateIp.isValidIp("115.300.10.60"));
        System.out.println(validateIp.isValidIp("50.35.6"));
        System.out.println(validateIp.isValidIp(".50.35.6"));
    }

}
