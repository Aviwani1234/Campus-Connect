package com.example.campus_connect_app.latestchatbot

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campus_connect_app.R
import com.example.campus_connect_app.databinding.ActivityChatBinding
import com.example.campus_connect_app.model.latestchat.MyMsg

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    var msgList = ArrayList<MyMsg>()
    private lateinit var myChatAdapter : MyChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        initChatAdapter()
        binding.refresh.setOnClickListener {
            myChatAdapter.notifyDataSetChanged()
            initChatAdapter()
        }
    }

    private fun initChatAdapter() {
        msgList.clear()
        val q1 = MyMsg("About College", "bot")
        val q2 = MyMsg("Courses", "bot")
        val q3 = MyMsg("Contact Details", "bot")
        val q4 = MyMsg("Brochure", "bot")
        msgList.add(q1)
        msgList.add(q2)
        msgList.add(q3)
        msgList.add(q4)
         myChatAdapter = MyChatAdapter(msgList)
        val lManager = LinearLayoutManager(this)
        binding.idRVChats.apply {
            adapter = myChatAdapter
            layoutManager = lManager
        }
        binding.idRVChats.fling(1, 1)

        myChatAdapter.setOnItemClickListener(object : MyChatAdapter.OnItemClickListener {
            override fun onItemClick(question: String, type: String) {
                if (type == "bot" && question == "About College") {

                    msgList.add(
                        MyMsg(
                            "About College -\n\t\t" +
                                    "Dr. D. Y. Patil Prathishthan's, D.Y. Patil College of Engineering, was established in 1984 in Pimpri and later shifted to Akurdi complex in 2001, which is in the vicinity of Pimpri Chinchwad Industrial area, one of the biggest Industrial belts in Asia. The college spreads over 10 acres of land with seven Engineering disciplines.\n\t\tPlease choose one of the below options to proceed further.\n",
                            "user"
                        )
                    )
                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.scrollToPosition(msgList.size - 1)
                } else if (type == "bot" && question == "Address") {
//                    myChatAdapter.clearData();
                    msgList.add(
                        MyMsg(
                            "Address -\n-\tD. Y. Patil College of Engineering, D. Y. Patil Educational Complex, Sector 29, Nigdi Pradhikaran, Akurdi, Pune 411044.",
                            "user"
                        )
                    )

                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                } else if (type == "bot" && question == "Courses") {
//                    myChatAdapter.clearData();
                    msgList.add(
                        MyMsg(
                            "Courses -\n",
                            "user"
                        )
                    )
                    val q1 = MyMsg("Computer Engineering", "bot")
                    val q2 = MyMsg("Information Technology", "bot")
                    val q3 = MyMsg("Electronics & Telecommunication", "bot")
                    val q4 = MyMsg("Instrumentation Engineering", "bot")
                    val q5 = MyMsg("Mechanical Engineering", "bot")
                    val q6 = MyMsg("Civil Engineering", "bot")
                    val q7 = MyMsg("Artificial Intelligence and Data Science", "bot")
                    val q8 = MyMsg("Robotics and Automation", "bot")
                    msgList.add(q1)
                    msgList.add(q2)
                    msgList.add(q3)
                    msgList.add(q4)
                    msgList.add(q5)
                    msgList.add(q6)
                    msgList.add(q7)
                    msgList.add(q8)
                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                } else if (type == "bot" && question == "Affiliations") {
//                    myChatAdapter.clearData();
                    msgList.add(
                        MyMsg(
                            "Affiliations \n-\tThis Institute is approved by AICTE, New Delhi and is affiliated to the Savitribai Phule Pune University.",
                            "user"
                        )
                    )

                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                } else if (type == "bot" && question == "Facilities") {
//                    myChatAdapter.clearData();
                    msgList.add(
                        MyMsg(
                            "Facilities \n\uD83D\uDC49 Hostel\n" +
                                    "\uD83D\uDC49 Canteen\n" +
                                    "\uD83D\uDC49 Play Ground\n" +
                                    "\uD83D\uDC49 Computer Center\n" +
                                    "\uD83D\uDC49 Library\n" +
                                    "\uD83D\uDC49 Language Laboratory\n" +
                                    "\uD83D\uDC49 Dnyanaprasad Sabhagruha\n" +
                                    "\uD83D\uDC49 Shantai Auditorium\n" +
                                    "\uD83D\uDC49 Amphi Theatre\n", "user"
                        )
                    )

                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                } else if (type == "bot" && question == "Contact Details") {
//                    myChatAdapter.clearData();
                    msgList.add(
                        MyMsg(
                            "Contacts - \n1.\tRecruitment : dypcoe_jobs@dypcoeakurdi.ac.in\n" +
                                    "2.\tTPO: tpo@dypcoeakurdi.ac.in , placements@dypcoeakurdi.ac.in\n" +
                                    "3.\tAdmission : dyppeca@gmail.com\n" +
                                    "4.\tEnquiry and Information: info@dypcoeakurdi.ac.in\n", "user"
                        )
                    )

                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                }
                else if (type == "bot" && question == "Brochure") {
//                    myChatAdapter.clearData();
                    msgList.add(
                        MyMsg(
                            "Brochure - \na)\tDYPCOE Brochure\n" +
                                    "-\thttps://oraistorage.blob.core.windows.net/dypatil/DYPCOE_final_college_leaflet_6.6.21.pdf\n" +
                                    "\n" +
                                    "b)\tCampus Brochure\n" +
                                    "-\thttps://www.dypcoeakurdi.ac.in/images/Downloads/CampusBroucher2020.pdf\n" +
                                    "\n", "user"
                        )
                    )

                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                }
                else if (type == "bot" && question == "Placement") {
//                    myChatAdapter.clearData();
                    msgList.add(
                        MyMsg(
                            "Placement - \n-\tPlease visit the site below to get all information\n" +
                                    "-\thttps://www.dypcoeakurdi.ac.in/t-p/placement-statistics\n",
                            "user"
                        )
                    )

                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                }
                else if (type == "bot" && question == "Computer Engineering") {
//                    myChatAdapter.clearData();
                    msgList.add(
                        MyMsg(
                            "Computer Engineering - \n" +
                                    "a.\tDuration:\n" +
                                    "-\t  Duration of B.E. Computer Engineering is 4 years.\n" +
                                    "\n" +
                                    "b.\tSyllabus:\n" +
                                    "-\tGo through the following links. https://bit.ly/3axnIg0\n" +
                                    "\n" +
                                    "c.\tPlacement :\n" +
                                    "-\tTo know about Placement Record please go through the given link: https://bit.ly/2WVLpKX",
                            "user"
                        )
                    )

                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                }
                else if (type == "bot" && question == "Information Technology") {
                    msgList.add(
                        MyMsg(
                            "Information Technology - \n" +
                                    "a.Duration:\n" +
                                    "-\t  Duration of B.E. Computer Engineering is 4 years.\n" +
                                    "\n" +
                                    "d.\tAbout:\n" +
                                    "-\tDYPCOE was one of the four colleges to start the Information Technology course under Savitribai Phule Pune University. Information Technology Department started with an intake of 30 in 1999.\n" +
                                    "\n" +
                                    "              c.Syllabus:\n" +
                                    "           -  Go through the following links. https://bit.ly/33XDpLi\n" +
                                    "\n" +
                                    "e.\tPlacement :\n" +
                                    "-\tTo know about Placement Record please go through the given link: https://bit.ly/2WVLpKX\n",
                            "user"
                        )
                    )

                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                }

                else if (type == "bot" && question == "Electronics & Telecommunication") {
                    msgList.add(
                        MyMsg(
                            "Electronics & Telecommunication - \n" +
                                   "a.Duration:\n" +
                                    "-\t  Duration of B.E. Computer Engineering is 4 years.\n" +
                                    "\n" +
                                    "f.\tAbout:\n" +
                                    "-\tElectronics and Telecommunication is a branch of Engineering that has remarkably influenced the life of the mankind. Its diversified advancement has evolved into various innovations which were impossible in the primitive ages.\n" +
                                    "\n" +
                                    "              c.Syllabus:\n" +
                                    "           -  Go through the following links. https://bit.ly/2JpWjkm\n" +
                                    "\n" +
                                    "g.\tPlacement :\n" +
                                    "-\tTo know about Placement Record please go through the given link: https://bit.ly/2WVLpKX\n",
                            "user"
                        )
                    )

                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                }

                else if (type == "bot" && question == "Instrumentation Engineering") {
                    msgList.add(
                        MyMsg(
                            "Instrumentation Engineering - \n" +
                                    "a.Duration:\n" +
                                    "-\t  Duration of B.E. Computer Engineering is 4 years.\n" +
                                    "\n" +
                                    "h.\tAbout:\n" +
                                    "The department of Instrumentation & Control was established in 1993 for the undergraduate (UG) program. It is affiliated to Savitribai Phule Pune University having an intake of 60. The department was accredited by the National Board of Accreditation w.e.f 05/08/2013 for two years.\n" +
                                    "\n" +
                                    "              c.Syllabus:\n" +
                                    "           -  Go through the following links. https://bit.ly/2UIgdwa\n" +
                                    "\n" +
                                    "d.Placement :\n" +
                                    "-\tTo know about Placement Record please go through the given link: https://bit.ly/2WVLpKX\n",
                            "user"
                        )
                    )

                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                }

                else if (type == "bot" && question == "Mechanical Engineering") {
                    msgList.add(
                        MyMsg(
                            "Mechanical Engineering - \n" +
                                    "a.Duration:\n" +
                                    "-\t  Duration of B.E. Computer Engineering is 4 years.\n" +
                                    "\n" +
                                    "i.\tAbout:\n" +
                                    "-\tMechanical Engineering Department is established since the inception of the college in 1984. Department consists of highly dedicated, motivated, qualified and competent faculty..\n" +
                                    "\n" +
                                    "              c.Syllabus:\n" +
                                    "           -  Go through the following links. https://bit.ly/2w02BEj\n" +
                                    "\n" +
                                    "d.Placement :\n" +
                                    "-\tTo know about Placement Record please go through the given link: https://bit.ly/2WVLpKX\n",
                            "user"
                        )
                    )
                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                }

                else if (type == "bot" && question == "Civil Engineering") {
                    msgList.add(
                        MyMsg(
                            "Civil Engineering - \n" +
                                    "a.Duration:\n" +
                                    "-\t  Duration of B.E. Computer Engineering is 4 years.\n" +
                                    "\n" +
                                    "j.\tAbout:\n" +
                                    "The Department of Civil Engineering is established in the year 1986. It is approved by AICTE, New Delhi and has received NBA Accreditation in Year 2003 & 2008 for 3 Yrs. The current intake for UG is 120 for the first shift and 60 for the second shift.\n" +
                                    "\n" +
                                    "              c.Syllabus:\n" +
                                    "           -  Go through the following links. https://bit.ly/2QSvppm\n" +
                                    "\n" +
                                    "d.Placement :\n" +
                                    "-\tTo know about Placement Record please go through the given link: https://bit.ly/2WVLpKX\n",
                            "user"
                        )
                    )
                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                }
                else if (type == "bot" && question == "Artificial Intelligence and Data Science") {
                    msgList.add(
                        MyMsg(
                            "Artificial Intelligence and Data Science - \n" +
                                    "a.Duration:\n" +
                                    "-\t  Duration of B.E. Computer Engineering is 4 years.\n" +
                                    "\n" +
                                    "k.\tAbout:\n" +
                                    "Today, when we look around, the technological advances in recent years have been immense. We can see driverless cars, hands-free devices that can turn on the lights, and robots working in factories, which prove that intelligent machines are possible. In the last four years in the Indian startup ecosystem, the terms that were used (overused rather) more than funding, valuation, and exit were Artificial Intelligence (AI) and Data Science.\n" +
                                    "\n" +
                                    "              c.Approved By:\n" +
                                    "           -  AICTE, New Delhi\n" +
                                    "\n" +
                                    "d.Placement :\n" +
                                    "-\tTo know about Placement Record please go through the given link: https://bit.ly/2WVLpKX\n",
                            "user"
                        )
                    )
                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                }
                else if (type == "bot" && question == "Robotics and Automation") {
                    msgList.add(
                        MyMsg(
                            "Robotics and Automation - \n" +
                                    "a.Duration:\n" +
                                    "-\t  Duration of B.E. Computer Engineering is 4 years.\n" +
                                    "\n" +
                                    "l.\tAbout:\n" +
                                    "- Today, when we look around, the technological advances in recent years have been immense. We can see driverless cars, hands-free devices that can turn on the lights, and robots working in factories, which prove that intelligent machines are possible. In the last four years in the Indian startup ecosystem, the terms that were used (overused rather) more than funding, valuation, and exit were Robotics and Automation.\n" +
                                    "\n" +
                                    "              c.Approved By:\n" +
                                    "           -  AICTE, New Delhi\n" +
                                    "\n" +
                                    "d.Placement :\n" +
                                    "-\tTo know about Placement Record please go through the given link: https://bit.ly/2WVLpKX\n",
                            "user"
                        )
                    )
                    myChatAdapter.notifyDataSetChanged()
                    binding.idRVChats.smoothScrollToPosition(msgList.size - 1)
                }

            }
        })
    }
}