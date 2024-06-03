package com.zyz.completebleFuture;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/5/11 17:16:14
 */
public class CMUtil {

    /**
     * 创建并行任务并执行
     *
     * @param list            数据源
     * @param function        api调用逻辑
     * @param exceptionHandle 异常处理逻辑
     * @return 处理结果列表
     */
    public static <S, T> List<T> parallelFutureJoin(Collection<S> list, Function<S, T> function, Consumer<Throwable> exceptionHandle) {
        List<CompletableFuture<T>> completableFutures = list.stream().map(s -> CompletableFuture.supplyAsync(() -> function.apply(s)))
                .collect(Collectors.toList());

        List<T> results = null;
        try {
            results = completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        } catch (Exception e) {
            exceptionHandle.accept(e);
            return null;
        }
        return results;
    }

}
