package assemble.api.club.repository;

import assemble.api.club.domain.Club;
import assemble.api.club.domain.QClub;
import assemble.api.club.domain.enums.ClubStatus;
import assemble.api.club.domain.enums.DifficultyLevel;
import assemble.api.member.domain.enums.InterestCategory;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
public class ClubRepositoryImpl implements ClubRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Club> findClubsBy(String region, InterestCategory category, DifficultyLevel level, boolean recruiting, String sort, Pageable pageable) {
        QClub club = QClub.club;
        List<Club> content = queryFactory
                .selectFrom(club)
                .where(
                        eqRegion(region),
                        eqCategory(category),
                        eqDifficultyLevel(level),
                        eqStatus(recruiting)
                )
                .orderBy(getSort(sort, club))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(club.count())
                .from(club)
                .where(
                        eqRegion(region),
                        eqCategory(category),
                        eqDifficultyLevel(level),
                        eqStatus(recruiting)
                )
                .fetchOne();
        return  new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression eqRegion(String region) {
        return region != null ? QClub.club.region.eq(region) : null;
    }

    private BooleanExpression eqCategory(InterestCategory category) {
        return category != null ? QClub.club.interestCategory.eq(category) : null;
    }

    private BooleanExpression eqDifficultyLevel(DifficultyLevel difficultyLevel) {
        return difficultyLevel != null ? QClub.club.level.eq(difficultyLevel) : null;
    }

    private BooleanExpression eqStatus(boolean status){
        return status ? QClub.club.status.eq(ClubStatus.RECRUTING) : null;
    }

    private OrderSpecifier<?> getSort(String sort, QClub club) {
        if("popular".equals(sort)){
            return club.curNumbers.desc();
        }
        if("latest".equals(sort)){
            return club.createdAt.desc();
        }
        return club.id.desc();
    }


}
