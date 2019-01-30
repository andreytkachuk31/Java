package context;

import annotation.MyInject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class MyInjectBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object object, String name) throws BeansException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(MyInject.class)) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, object, this.beanFactory.getBean(field.getType()));
            }
        }
        return object;
    }

    @Override
    public Object postProcessAfterInitialization(Object object, String name) throws BeansException {
        return object;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
