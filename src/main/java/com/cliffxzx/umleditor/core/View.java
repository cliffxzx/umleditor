package com.cliffxzx.umleditor.core;

import javax.swing.*;

/**
 * A class to designate view for MVC.
 */
abstract public class View<M extends Model<?>> {

    /**
     * This method should create the views components
     *
     * @param model the model to base the view off of initially
     */
    public abstract void create(M model);

    public abstract JPanel getGui();
}
