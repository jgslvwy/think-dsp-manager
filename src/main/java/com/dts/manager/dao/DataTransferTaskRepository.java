package com.dts.manager.dao;

import com.dts.manager.model.DataTask;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DataTransferTaskRepository extends PagingAndSortingRepository<DataTask, String> {
}
