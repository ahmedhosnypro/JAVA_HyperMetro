import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * Get sorted list of private fields the object declares (inherited fields should be skipped).
 */

class FieldGetter {

    public List<String> getPrivateFields(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(f -> Modifier.isPrivate(f.getModifiers()))
                .map(Field::getName)
                .sorted()
                .toList();
    }

}