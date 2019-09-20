package main.java;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 单例工厂，用于返回所有自定义类单例对象（多线程安全）
 *
 * @author chy
 */
public class SingletonFactory {
    public static final Logger LOG = Logger.getLogger(SingletonFactory.class.getCanonicalName());//用于日志输出
    private static final Map<Constructor<?>, Object> INSTANCE_MAP = new ConcurrentHashMap<>();/*Collections.synchronizedMap(new HashMap<Constructor<?>, Object>());*///用于存放单例对象
    private static final Object[] EMPTY_ARGS = new Object[0];  //给空参构造使用

    /**
     * 返回一个无参对象
     * 用此方法有一个前提:
     * 参数类必须有显示声明的无参构造器或没有任何显示声明的构造器
     */
    public static <T> T getInstance(Class<? extends T> clazz) {
        return getInstance(clazz, EMPTY_ARGS);
    }

    /**
     * 返回一个有参对象
     * 用此方法有3个前提:
     * 1. 参数数组的项都不能为null
     * 2. 参数数组的项都不能是基本数据类型
     * 3.args接收的必须是超类数组，里面存放具体的参数值，用法如：.getInstance(Test1.class,new Object[]{"小米","男",21})
     */
    public static <T> T getInstance(Class<? extends T> clazz, Object... args) {
        Constructor<?> constructor = getConstructor(clazz, args);
        if (constructor == null) {
            return null;
        }
        Object object = INSTANCE_MAP.get(constructor);
        if (object == null) {
            return getInstance(clazz, constructor, args);
        }
        return clazz.cast(object);//将制定类型装换并返回
    }

    /**
     * 根据传入参数创建有参或者无参构造并返回
     *
     * @param clazz 字节码文件对象
     * @param args  参数值集
     * @return 返回一个构造器
     */
    private static Constructor<?> getConstructor(Class<?> clazz, Object... args) {
        Constructor<?> constructor = null;
        try {
            if (args.length > 0) {
                Class<?>[] parameterTypes = new Class<?>[args.length];
                for (int i = 0; i < args.length; i++) {
                    parameterTypes[i] = args[i].getClass();
                }
                if (parameterTypes.length == args.length) {
                    constructor = clazz.getDeclaredConstructor(parameterTypes);//获取单个构造方法，包括私有构造
                } else {
                    throw new IllegalArgumentException("参数个数不匹配");
                }
            } else {
                constructor = clazz.getDeclaredConstructor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return constructor;
    }

    /**
     * @param clazz       字节码对象
     * @param constructor 构造器
     * @param args        参数值集
     * @param <T>         指定类型
     * @return 返回指定类型对象
     */
    private static <T> T getInstance(Class<? extends T> clazz, Constructor<?> constructor, Object[] args) {
        synchronized (clazz) {// 双重检查，以保证每个线程都是从主存中读取最新的数据
            T instance;
            try {
                constructor.setAccessible(true);//值为true指示反射的对象在使用时应该取消Java语言访问检查。
                if (args.length == 0) {
                    instance = clazz.cast(constructor.newInstance());//构造无参对象
                } else {
                    instance = clazz.cast(constructor.newInstance(args));//构造有参对象
                }
                INSTANCE_MAP.put(constructor, instance);//创建完成后保存该单例对象
                return clazz.cast(instance);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "创建实例失败", e);
            }
        }
        return null;
    }
}

