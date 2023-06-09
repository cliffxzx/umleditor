package com.cliffxzx.umleditor.core;

import com.cliffxzx.umleditor.runner.GUI;
import com.cliffxzx.umleditor.util.Navigator;

/**
 * A base core.Controller class for MVC.
 */
public abstract class Controller<V extends View<M>, M extends Model<?>> {
    protected V view;
    protected M model;

    /**
     * Create the controller with the view and model
     *
     * @param view  the view (gui)
     * @param model the model (data)
     */
    public Controller(V view, M model) {
        this.view = view;
        this.model = model;
    }

    /**
     * This should be used for initialization logic.
     * This includes binding event listeners, and loading in data.
     *
     * @param nav the navigator (allow controller to change to a different
     *            <code>Screen</code>)
     */
    abstract protected void onInit(Navigator nav);

    /**
     * This should be used to clean up after your controller is done with (navigated
     * to a new <code>Screen</code>).
     */
    abstract protected void onCleanup();

    /**
     * Get the view for <code>runner.GUI</code> to render it.
     *
     * @return the controllers view
     * @see GUI
     */
    public V getView() {
        return view;
    }

    /**
     * An internal delegate for the <code>onInit</code> method.
     *
     * @param nav the navigator to be passed to the controller
     */
    public void init(Navigator nav) {
        onInit(nav);
    }

    /**
     * An internal delegate for the <code>onClose</code> method.
     */
    public void cleanup() {
        onCleanup();
    }
}
