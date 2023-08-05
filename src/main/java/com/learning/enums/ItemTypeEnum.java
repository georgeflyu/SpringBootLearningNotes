package com.learning.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public enum ItemTypeEnum {

    STORY("story", 1, "story_", 96.7) {
        @Override
        public String generateCompleteName(String name) {
            return this.getItemPrefix() + name;
        }
    },

    APP("APP", 2, "app_", 96.7) {
        @Override
        public String generateCompleteName(String name) {
            return this.getItemPrefix() + name;
        }
    },

    PRODUCT("product", 3, "product_", 96.7) {
        @Override
        public String generateCompleteName(String name) {
            return this.getItemPrefix() + name;
        }
    },

    SERVICE("service", 4, "service_", 96.7) {
        @Override
        public String generateCompleteName(String name) {
            return this.getItemPrefix() + name;
        }
    };


    public static final Map<String, ItemTypeEnum> ITEM_TYPE_ENUM_MAP = Collections.unmodifiableMap(
        Arrays.stream(ItemTypeEnum.values()).collect(Collectors.toMap(ItemTypeEnum::getItemName, Function.identity())));


    public ItemTypeEnum getItemTypeEnumByItemName(String itemName) {
        return Optional.ofNullable(ITEM_TYPE_ENUM_MAP.get(itemName)).orElseThrow(() -> new RuntimeException("no such item type"));
    }


    private final String itemName;

    private final Integer itemId;

    private final String itemPrefix;

    private final Double itemScore;

    public abstract String generateCompleteName(String name);

    ItemTypeEnum(String itemName, Integer itemId, String itemPrifix, Double itemScore) {
        this.itemName   = itemName;
        this.itemId     = itemId;
        this.itemPrefix = itemPrifix;
        this.itemScore  = itemScore;
    }
}
