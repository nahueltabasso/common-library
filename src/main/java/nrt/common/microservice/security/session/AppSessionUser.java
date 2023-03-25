package nrt.common.microservice.security.session;

import nrt.common.microservice.security.dto.CommonUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * This class return a current user login on the Application.
 *
 * @author nahueltabasso
 */
public class AppSessionUser {

    public static CommonUserDetails getCurrentAppUser() {
        try {
            return (CommonUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
