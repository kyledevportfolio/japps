package com.sayson.narl.imessage.mods


import java.util.ArrayList

class userAcc(contact:ArrayList<Contacts>, Email:String, Password:String, PhoneNum:String, Username:String ) {

    var contact = contact
    var Email = Email
    var Password = Password
    var PhoneNum = PhoneNum
    var Username = Username



    constructor() : this(ArrayList<Contacts>(),"","","","")


}