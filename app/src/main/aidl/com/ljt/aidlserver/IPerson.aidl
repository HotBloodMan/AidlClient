// IPerson.aidl
package com.ljt.aidlserver;

// Declare any non-default types here with import statements

interface IPerson {
    void setAge(int age);
    void setName(String name);
    String display();
}
