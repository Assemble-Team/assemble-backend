package assemble.api.club.business.validator;

import assemble.api.member.domain.enums.Role;

public class MemberClubValidator {

    public static Role parseRole(String role){
        if(role.equals("LEADER")){
            return Role.LEADER;
        }
        else if(role.equals("MANAGER")){
            return Role.MANAGER;
        }
        else if(role.equals("MEMBER")){
            return Role.MEMBER;
        }
        return null;
    }
}
