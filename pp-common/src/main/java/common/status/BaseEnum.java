package common.status;

/**
 * 所有项目状态枚举都必须实现这个接口，数据库直接存入枚举量存入的值
 *
 * @param <T>
 * @author David
 */
public interface BaseEnum<T extends Comparable<?>> {
    T getValue();
}