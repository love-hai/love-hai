package com.LoveSea.fengCore.study.jdk.enumMap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiahaifeng
 */

public class Plant {
    enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL}

    final String name;
    final LifeCycle lifeCycle;

    Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        List<Plant> garden = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            garden.add(new Plant("plant" + i,
                    Plant.LifeCycle.ANNUAL));
            garden.add(new Plant("plant" + i,
                    Plant.LifeCycle.PERENNIAL));
            garden.add(new Plant("plant" + i,
                    Plant.LifeCycle.BIENNIAL));
        }
        // 使用Set<Plant>[]数组来表示植物的生命周期
        Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];
        for (int i = 0; i < plantsByLifeCycle.length; i++) {
            plantsByLifeCycle[i] = new HashSet<>();
        }
        for (Plant p : garden) {
            plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
        }
        for (int i = 0; i < plantsByLifeCycle.length; i++) {
            System.out.printf("%s: %s%n",
                    Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
        }
        // 使用EnumMap来关联数据和枚举
        Map<LifeCycle, Set<Plant>> plantsByLifeCycleMap =
                new EnumMap<>(Plant.LifeCycle.class);
        for (Plant.LifeCycle lc : Plant.LifeCycle.values())
            plantsByLifeCycleMap.put(lc, new HashSet<>());
        for (Plant p : garden)
            plantsByLifeCycleMap.get(p.lifeCycle).add(p);
        System.out.println(plantsByLifeCycleMap);
        Map<LifeCycle, Set<Plant>> plantsByLifeCycleMap2 = garden.stream().collect(Collectors.groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(LifeCycle.class), Collectors.toSet()));
    }
}