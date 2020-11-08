package com.example.springboot.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Auther: 59315
 * @Date: 2020/6/28 23:46
 * @Description: 布隆过滤器
 */
public class BloomFilterTest {

    // 初始化一个能够容纳10000个元素且容错率为0.01布隆过滤器
    private static final BloomFilter<Integer> bloomFilter =
            BloomFilter.create(Funnels.integerFunnel(), 10000, 0.01);

    /**
     * 初始化布隆过滤器
     */
    private static void initLegalIdsBloomFilter() {
        // 初始化10000个合法Id并加入到过滤器中
        for (int legalId = 0; legalId < 10000; legalId++) {
            bloomFilter.put(legalId);
        }
    }

    /**
     * id是否合法有效，即是否在过滤器中
     *
     * @param id
     * @return
     */
    public static boolean validateIdInBloomFilter(Integer id) {
        return bloomFilter.mightContain(id);
    }

    public static void main(String[] args) {
        // 初始化过滤器
        initLegalIdsBloomFilter();
        // 误判个数
        int errorNum=0;
        // 验证从10000个非法id是否有效
        for (int id = 10000; id < 20000; id++) {
            if (validateIdInBloomFilter(id)){
                // 误判数
                errorNum++;
            }
        }
        System.out.println("judge error num is : " + errorNum);
    }
}
