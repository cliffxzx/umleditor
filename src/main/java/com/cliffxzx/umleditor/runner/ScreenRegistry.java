package com.cliffxzx.umleditor.runner;

import com.cliffxzx.umleditor.core.Controller;
import com.cliffxzx.umleditor.core.Model;
import com.cliffxzx.umleditor.core.View;
import com.cliffxzx.umleditor.util.AnnotationFinder;
import com.cliffxzx.umleditor.util.Navigator;
import com.cliffxzx.umleditor.util.ReflectionsServiceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A registry of every screen for the application.
 */
public class ScreenRegistry {
    private static ScreenRegistry registry = null;
    private final AnnotationFinder finder;
    private final Map<String, ScreenCreator<?, ?, ?>> screens = new HashMap<>();
    private String entryPoint = null;

    private ScreenRegistry(AnnotationFinder annotationFinder) {
        finder = annotationFinder;
    }

    /**
     * A method to register a <code>runner.Screen</code>.
     *
     * @param screen       the screen to register
     * @param isEntryPoint true if the screen is the entry point
     * @param <M>          the class of the model, extends <code>core.Model</code>
     * @param <V>          the class of the view, extends <code>core.View</code>
     * @param <C>          the class of the controller, extends
     *                     <code>core.Controller</code>
     */
    public <M extends Class<? extends Model>, V extends Class<? extends View>, C extends Class<? extends Controller>> void addScreen(
            ScreenCreator<M, V, C> screen, boolean isEntryPoint) {
        if (!screens.containsKey(screen.getName())) {
            screens.put(screen.getName(), screen);
        }
        if (isEntryPoint) {
            if (entryPoint == null) {
                entryPoint = screen.getName();
            } else {
                throw new DuplicateScreenException("Screen " + screen.getName()
                        + " cannot be set as the entrypoint because " + entryPoint + "is the entry point.");
            }
        }
    }

    /**
     * create a new ScreenRegistry if it doesn't exist, or return the current one.
     *
     * @return the current ScreenRegistry
     */
    public static ScreenRegistry getInstance() {
        if (registry == null) {
            registry = new ScreenRegistry(new AnnotationFinder(new ReflectionsServiceImpl()));
        }
        return registry;
    }

    /**
     * A method to see if the ScreenRegistry contains the specified screen.
     * This method is used by navigator to check for screen existence.
     *
     * @param name the name of the screen creator
     * @return if the ScreenRegistry has a screen creator with that name
     * @see Navigator
     */
    public boolean containsScreen(String name) {
        return screens.containsKey(name);
    }

    /**
     * Call <code>Create()</code> on the screen creator with the specified name.
     *
     * @param name the name of the screen creator
     * @return the result of <code>Create()</code>
     */
    public Screen createFromName(String name) {
        return screens.get(name).create();
    }

    /**
     * Call <code>Create()</code> on the first found screen creator.
     * Not guaranteed to be the same screen creator when run multiple times.
     * This will be fixed in the future.
     *
     * @return the result of <code>Create()</code>
     * @throws NoScreensException when there are no screens in the registry
     */
    public Screen createEntryPoint() throws NoScreensException {
        if (entryPoint == null)
            throw new NoScreensException("No entrypoint defined. use @EntryPoint on your model, view or controller.");
        return screens.get(entryPoint).create();

    }

    /**
     * Adds all the screens with the <code>@MVC</code> annotation in the classpath
     * to the registry
     */
    public void addScreensFromClassPath() {
        try {
            Arrays.stream(new ScreenFinder(finder).find()).forEach(tuple2 -> addScreen(tuple2.first, tuple2.second));
        } catch (ScreenMissingPartsException | DuplicateScreenException e) {
            e.printStackTrace();
        }
    }
}
