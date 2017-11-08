idea-sourcetrail
==========

Sourcetrail plugin for idea IDEs like Intellij or CLion. Provides synchronization with Sourcetrail_.

.. _Sourcetrail: https://sourcetrail.com

Install
-------

1) **File -> Settings...** or **Ctrl + Alt + s** to open the Settings
2) Go to Plugins and click Browser Repositories...
3) search for sourcetrail
4) install idea-sourcetrail

Usage
-----

From Sourcetrail to IDE
~~~~~~~~~~~~~~~~~

Right click in sourcetrail > Set IDE Curor | In the IDE should now open the file and put the cursor in the position form sourcetrail.

From IDE to Sourcetrail
~~~~~~~~~~~~~~~~~
Navigate your textcursor to the location.

* Rightclick -> Send location

Preferences
-----------

* **File -> Settings...** or **Crtl + Alt + s** to open the Settings
* **OtherSettings -> Sourcetrail Settings**

Ports and Ip
~~~~~~~~~~~~

You can change the ports and ip.
Make sure you use the same settings in Sourcetrail

Keyboard shortcut
~~~~~~~~~~~~~~~~~

* **File -> Settings...** or **Ctrl + Alt + s** to open the Settings
* Go to Keymaps and search for sourcetrail
* Add Shortcut to **Send Location**


Building
--------

This Plugin is build with Gradle_. Before running any Gradle tasks on this project, please add a ``gradle.properties`` file to the project that defines the ``publishUsername`` and the ``publishPassword`` variables. If you don't plan on publishing the plugin, you can define them as empty.

.. _Gradle: https://gradle.org

Simply run:

    gradle buildPlugin

or run:

    gradle tasks

to see what other tasks are available.

