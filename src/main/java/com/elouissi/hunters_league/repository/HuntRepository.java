package com.elouissi.hunters_league.repository;

import com.elouissi.hunters_league.domain.Hunt;
import com.elouissi.hunters_league.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HuntRepository extends JpaRepository<Hunt,Long> {
    public void deleteByParticipationUser(User user);
}
