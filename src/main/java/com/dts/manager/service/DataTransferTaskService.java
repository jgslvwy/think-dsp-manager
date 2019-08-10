package com.dts.manager.service;

import com.dts.manager.model.DataTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Mono;

public interface DataTransferTaskService {
    /**
     * save migration task
     *
     * @param dataTask
     * @return
     * @author adao
     */
    Mono<DataTask> save(DataTask dataTask);

    /**
     * query task list with paging
     * consider that the status of the migration task changes in real time,
     * and the response flow is used to refresh the background progress of
     * the background and front end in real time.
     *
     * <p>short polling:Use ajax to periodically request from the server,
     * regardless of whether the data is updated immediately and return data,
     * high concurrency may put pressure on the server and bandwidth
     * <p>long polling:Using comet to continuously initiate a request to the
     * server, the server suspends the request until the new data is returned,
     * and the relatively short polling reduces the number of requests
     * <p>
     * SSE:Server Send Event, the connection is maintained after the client
     * initiates a request, and the server continues to send data to the client
     * based on the connection, starting from HTML5.
     * <p>
     * Websocket:This is also a technique for staying connected, and it is two-way,
     * starting with HTML5, not completely based on HTTP, suitable for two-way
     * communication scenarios with frequent and large traffic.
     *
     * <see></>
     *
     * @return
     * @author adao
     */
    Page<DataTask> searchDataTask(PageRequest pageRequest);

    /**
     * delete an instance based on id, if the mode is in operation or in the end state is not operational
     *
     * @param taskId
     * @return
     * @author adao
     */
    Mono<Boolean> delete(String taskId);

    /**
     * open a migration task
     * <pre>if the task is running or has ended, it cannot be migrated again</pre>
     * <pre>the progress of the task is captured during the startup process</pre>
     *
     * @param dataTask
     * @return
     */
    Mono<Boolean> start(DataTask dataTask);
}
