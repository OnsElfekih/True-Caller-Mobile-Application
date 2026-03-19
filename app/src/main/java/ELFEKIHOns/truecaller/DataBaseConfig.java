package ELFEKIHOns.truecaller;

public class DataBaseConfig {
    public static final String ipServeur ="192.168.1.3"; // Remplacez par votre IPv4 (ipconfig)
    public static final String port="80";
    public static final String URL_GetAll="http://"+ipServeur+":"+port+"/servicephp/getall.php";
    public static final String URL_AddPosition="http://"+ipServeur+":"+port+"/servicephp/addposition.php";
}
