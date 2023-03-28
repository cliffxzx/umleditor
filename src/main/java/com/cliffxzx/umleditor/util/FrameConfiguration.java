package com.cliffxzx.umleditor.util;

import com.cliffxzx.umleditor.runner.GUI;

/**
 * An interface to allow for configuration of the underlying GUI, which is a
 * subclass of JFrame
 */
public interface FrameConfiguration {
    /**
     * Run to configure the gui
     *
     * @param gui the GUI to configure
     */
    void config(GUI gui);
}
