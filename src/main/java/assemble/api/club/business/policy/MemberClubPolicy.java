package assemble.api.club.business.policy;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.ClubErrorStatus;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.member.domain.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class MemberClubPolicy {

    public void validateLeaderOrManager(MemberClub memberClub) {
        if(memberClub.getRole() == Role.MEMBER){
            throw new GeneralException(ClubErrorStatus.CREATE_NOTICE_PERMISSION_DENIED);
        }
    }

}
