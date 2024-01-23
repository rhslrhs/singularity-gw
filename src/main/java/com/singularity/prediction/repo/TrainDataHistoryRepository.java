package com.singularity.prediction.repo;

import com.singularity.prediction.entity.TrainDataHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainDataHistoryRepository extends JpaRepository<TrainDataHistory, Long> {

}
