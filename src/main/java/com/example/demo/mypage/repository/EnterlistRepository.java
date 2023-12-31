package com.example.demo.mypage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.info.entity.InfoEntity;
import com.example.demo.mypage.entity.EnterlistEntity;

public interface EnterlistRepository extends JpaRepository<EnterlistEntity, Long>{
	@Query(value="SELECT e.*, i.title FROM enterlist e, information i"
			+ " WHERE e.info_seq= i.info_seq  AND e.email=:email ORDER BY e.enter_seq DESC", nativeQuery=true)
	List<EnterlistEntity> findAll(@Param("email") String email);
	

	@Query(value="INSERT INTO enterlist SELECT enterlist_seq.nextval, 0, :infoSeq, :email FROM dual"
			+ " WHERE NOT EXISTS(SELECT info_seq, email FROM enterlist WHERE info_seq=:infoSeq AND email=:email)", nativeQuery=true)
	@Modifying
	void findSaveNew(@Param("infoSeq") Long infoSeq, @Param("email") String email);
	
	@Query(value="UPDATE enterlist SET completed=:#{#entity.completed} WHERE enter_seq=:#{#entity.enterSeq}", nativeQuery=true)
	@Modifying
	void findByUpdateEntity(@Param("entity") EnterlistEntity entity);
}