package com.sayson.narl.imessage.mods

import java.util.*



class ThisMessage(Messages: String, Receiver: String, Sender: String,SenderName: String,TimeStamp: Date) {

    var Messages = Messages

    var Reciever = Receiver
    var Sender = Sender
    var myemail = ""
    var SenderName = SenderName
    var TimeStamp= TimeStamp
    var countOfMsgs = 0


    constructor() : this("", "", "","", Date())
}