package nrt.common.microservice.utils;

import java.util.List;

public class ListUtils {

    /**
     * Return true when the list that receive such as parameter is null or is empty
     * @param list
     * @return
     */
    public static boolean isNullOrEmpty(List<?> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
}
