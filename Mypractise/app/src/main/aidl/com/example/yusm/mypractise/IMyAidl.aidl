// IMyAidl.aidl
package com.example.yusm.mypractise;

// Declare any non-default types here with import statements
import com.example.yusm.mypractise.bean.Person;

interface IMyAidl {

    void addPerson(in Person person);

    List<Person> getPersonList();

}
