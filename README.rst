idea-coati
==========

idea-coati is a plugin for Jetbrain IDEs to communicate with Coati_.

.. _Coati: https://coati.io

Install
-------

1) **File -> Settings...** or **Ctrl + Alt + s** to open the Settings
2) Go to Plugins and click Browser Repositories...
3) search for coati
4) install idea-coati

Usage
-----

From Coati to IDE
~~~~~~~~~~~~~~~~~

Right click in coati > Set IDE Curor | In the IDE should now open the file and put the cursor in the position form coati.

From IDE to Coati
~~~~~~~~~~~~~~~~~
Navigate your textcursor to the location.

* Rightclick -> Send location

Preferences
-----------

* **File -> Settings...** or **Crtl + Alt + s** to open the Settings
* **OtherSettings -> Coati Settings**

Ports and Ip
~~~~~~~~~~~~

You can change the ports and ip.
Make sure you use the same settings in Coati

Keyboard shortcut
~~~~~~~~~~~~~~~~~

* **File -> Settings...** or **Ctrl + Alt + s** to open the Settings
* Go to Keymaps and search for coati
* Add Shortcut to **Send Location**


Building
--------

This Plugin is build with Gradle_

.. _Gradle: https://gradle.org

Simply run:

    gradle buildPlugin

or run:

    gradle tasks

to see what other tasks are available.

