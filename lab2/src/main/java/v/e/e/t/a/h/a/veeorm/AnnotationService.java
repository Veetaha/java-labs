package v.e.e.t.a.h.a.veeorm;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

class AnnotationService {

    public static <TAnnotation extends Annotation>
    TAnnotation getAnnotationOrFail(
        AnnotatedElement elem,
        Class<TAnnotation> annotClass
    ) throws Exception {
        var annotation = elem.getAnnotation(annotClass);
        if (annotation != null) return annotation;

        throw new Exception(String.format(
            "Expected %s annotation on %s",
            annotClass.toString(),
            elem.toString()
        ));
    }

}
