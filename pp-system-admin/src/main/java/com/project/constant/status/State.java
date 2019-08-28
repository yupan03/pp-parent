package com.project.constant.status;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import common.status.BaseStatusEnum;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum State implements BaseStatusEnum<Integer> {
    All(-1, "全部"),

    NO(0, "停用"),

    YES(1, "正常");

    private final Integer value;
    @Getter
    private final String desc;

    @JsonCreator
    public static State getItem(Integer value) {
        for (State state : values()) {
            if (state.getValue().equals(value)) {
                return state;
            }
        }
        return null;
    }

    State(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}