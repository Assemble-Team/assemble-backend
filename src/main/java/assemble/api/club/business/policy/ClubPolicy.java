package assemble.api.club.business.policy;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.ClubErrorStatus;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.enums.DifficultyLevel;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.club.repository.MemberClubRepository;
import assemble.api.member.domain.Member;
import assemble.api.member.domain.enums.InterestCategory;
import assemble.api.member.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClubPolicy {

    private final MemberClubRepository memberClubRepository;

    public static DifficultyLevel parseLevel(String level){
        if(level.equals("LOW")){
            return DifficultyLevel.LOW;
        }
        else if(level.equals("MID")){
            return DifficultyLevel.MID;
        }
        else if(level.equals("HIGH")){
            return DifficultyLevel.HIGH;
        }
        else{
            throw new GeneralException(ClubErrorStatus.INVALID_DIFFICULTY_LEVEL);
        }
    }

    public static InterestCategory parseCategory(String category){
        if(category.equals("STUDY")){
            return InterestCategory.STUDY;
        }
        else if(category.equals("EXERCISE")){
            return InterestCategory.EXERCISE;
        }
        else if(category.equals("PROJECT")){
            return InterestCategory.PROJECT;
        }
        else if(category.equals("HOBBY")){
            return InterestCategory.HOBBY;
        }
        else if(category.equals("CULTURE_ART")){
            return InterestCategory.CULTURE_ART;
        }
        else{
            throw new GeneralException(ClubErrorStatus.INVALID_INTEREST_CATEGORY);
        }
    }

    public void validateUpdateInfo(Club club, Member member){
        MemberClub memberClub = memberClubRepository.findByMemberAndClub(member, club)
                .orElseThrow(() -> new GeneralException(ClubErrorStatus.NOT_JOIN_MEMBER_AND_CLUB));
        if(memberClub.getRole() != Role.LEADER){
            throw new GeneralException(ClubErrorStatus.UPDATE_PERMISSION_DENIED);
        }
    }
}
