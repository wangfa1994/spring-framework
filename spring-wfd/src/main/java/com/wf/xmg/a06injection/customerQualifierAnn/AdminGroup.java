package com.wf.xmg.a06injection.customerQualifierAnn;
import org.springframework.beans.factory.annotation.Qualifier;
import java.lang.annotation.*;

/**
 * // 管理员组
 *
 * 用户组注解，扩展 {@link Qualifier @Qualifier}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface AdminGroup {



}
