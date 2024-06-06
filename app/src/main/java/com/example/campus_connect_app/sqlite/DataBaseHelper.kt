import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.util.Log
import com.example.campus_connect_app.R
import java.io.ByteArrayOutputStream
import com.example.campus_connect_app.model.Complaint
import com.example.campus_connect_app.model.placement.AppliedStudents
import com.example.campus_connect_app.model.placement.CampusDrive

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        db.execSQL(SQL_CREATE_DRIVE)
        db.execSQL(SQL_CREATE_APPLIED_STUD)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        db.execSQL(SQL_CREATE_DRIVE)
        db.execSQL(SQL_CREATE_APPLIED_STUD)
        onCreate(db)
    }

    fun insertComplaint(complaint: Complaint): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, complaint.userName)
            put(COLUMN_DEPARTMENT, complaint.department)
            put(COLUMN_STATUS, complaint.status)
            put(COLUMN_REPLY, complaint.reply)
            put(COLUMN_IMAGE, complaint.image) // Convert image to byte array
            put(COLUMN_DESCRIPTION, complaint.description)
        }
        val newRowId = db.insert(TABLE_COMPLAINTS, null, values)
        db.close()
        return newRowId != -1L
    }

    fun insertCampusDrive(campusDrive : CampusDrive) : Boolean{
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ROLE, campusDrive.role)
            put(COLUMN_COMPANY_NAME, campusDrive.companyName)
//            put(COLUMN_DESCRIPTION, campusDrive.companyDescription)
            put(COLUMN_LOCATION, campusDrive.location)
            put(COLUMN_COMPANY_DECSCRIPTION, campusDrive.companyDescription)
            put(COLUMN_FROM_DATE, campusDrive.fromDate)
            put(COLUMN_TO_DATE, campusDrive.toDate)
            put(COLUMN_JOB_TYPE, campusDrive.jobType)
            put(COLUMN_HIRING_COUNT, campusDrive.hiringCount)
            put(COLUMN_REQUIREMENTS, campusDrive.requirements)
            put(COLUMN_REQUIREMENTS_EDU, campusDrive.requirementsEdu)
            put(COLUMN_SALARY, campusDrive.salary)
            put(COLUMN_EXPEREIENCE, campusDrive.experience)
            put(COLUMN_ROUNDS, campusDrive.rounds)
        }
        val newRowId = db.insert(TABLE_CAMPUS_DRIVE, null ,values)
        db.close()
        return newRowId != -1L
    }
    fun insertAppliedStudent(appliedStudents: AppliedStudents) : Boolean{
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_STUDENT_ID, appliedStudents.studentId)
            put(COLUMN_CAMPUS_DRIVE_ID, appliedStudents.campusDriveId)
//            put(COLUMN_DESCRIPTION, campusDrive.companyDescription)
            put(COLUMN_FULL_NAME, appliedStudents.fullName)
            put(COLUMN_DOB, appliedStudents.dob)
            put(COLUMN_ADDRESS, appliedStudents.address)
            put(COLUMN_PHONE, appliedStudents.phone)
            put(COLUMN_EMAIL, appliedStudents.email)
            put(COLUMN_GENDER, appliedStudents.gender)
            put(COLUMN_COURSE, appliedStudents.course)
            put(COLUMN_INSTITUTE, appliedStudents.institute)
            put(COLUMN_UNIVERSITY, appliedStudents.university)
            put(COLUMN_YEAR_OF_PASSING, appliedStudents.yearOfPassing)
            put(COLUMN_PERCENTAGE, appliedStudents.percentage)
            put(COLUMN_PDF, appliedStudents.pdf)
        }
        val newRowId = db.insert(TABLE_APPLIED_STUD, null ,values)
        db.close()
        return newRowId != -1L
    }

    fun getAllAppliedStudents(): List<AppliedStudents> {
        val appliedStudent = mutableListOf<AppliedStudents>()
        val db = readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM $TABLE_APPLIED_STUD", null)
        cursor?.use {
            while (it.moveToNext()) {
                val studentId = it.getInt(it.getColumnIndexOrThrow(COLUMN_STUDENT_ID))
                val campusDriveId = it.getInt(it.getColumnIndexOrThrow(COLUMN_CAMPUS_DRIVE_ID))
                val fullName = it.getString(it.getColumnIndexOrThrow(COLUMN_FULL_NAME))
                val dob = it.getString(it.getColumnIndexOrThrow(COLUMN_DOB))
                val address = it.getString(it.getColumnIndexOrThrow(COLUMN_ADDRESS))
                val phone = it.getString(it.getColumnIndexOrThrow(COLUMN_PHONE))
                val email = it.getString(it.getColumnIndexOrThrow(COLUMN_EMAIL))
                val gender = it.getString(it.getColumnIndexOrThrow(COLUMN_GENDER))
                val course = it.getString(it.getColumnIndexOrThrow(COLUMN_COURSE))
                val institute = it.getString(it.getColumnIndexOrThrow(COLUMN_INSTITUTE))
                val university = it.getString(it.getColumnIndexOrThrow(COLUMN_UNIVERSITY))
                val yearOfPassing = it.getString(it.getColumnIndexOrThrow(COLUMN_YEAR_OF_PASSING))
                val percentage = it.getString(it.getColumnIndexOrThrow(COLUMN_PERCENTAGE))
                val pdf = it.getBlob(it.getColumnIndexOrThrow(COLUMN_PDF))

                val appliedStud = AppliedStudents(
                    studentId, campusDriveId,fullName,dob,address, phone, email, gender, course, institute, university, yearOfPassing, percentage, pdf
                )


                appliedStudent.add(appliedStud)
            }
        }
        cursor?.close()
        return appliedStudent
    }


    fun getAllComplaints(): List<Complaint> {
        val complaints = mutableListOf<Complaint>()
        val db = readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM $TABLE_COMPLAINTS", null)
        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getString(it.getColumnIndexOrThrow(COLUMN_ID))
                val userName = it.getString(it.getColumnIndexOrThrow(COLUMN_USERNAME))
                val title = it.getString(it.getColumnIndexOrThrow(COLUMN_TITLE))
                val department = it.getString(it.getColumnIndexOrThrow(COLUMN_DEPARTMENT))
                val status = it.getString(it.getColumnIndexOrThrow(COLUMN_STATUS))
                val reply = it.getString(it.getColumnIndexOrThrow(COLUMN_REPLY))
//                val imageByteArray = it.getBlob(it.getColumnIndexOrThrow(COLUMN_IMAGE))
//                val image = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
                val image = it.getInt(it.getColumnIndexOrThrow(COLUMN_IMAGE))
                val description = it.getString(it.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val complaint = Complaint(
                    id,
                    userName,
                    title,
                    department,
                    status,
                    reply,
//                    image,
                    R.drawable.add_image,
                    description
                )

                complaints.add(complaint)
            }
        }
        cursor?.close()
        return complaints
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }

    fun getAllDrives() : List<CampusDrive>{
        val drives = mutableListOf<CampusDrive>()
        val db = readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM $TABLE_CAMPUS_DRIVE", null)
        cursor?.use {
            try {
                while (it.moveToNext()){
                    val driveId = it.getInt(it.getColumnIndexOrThrow(COLUMN_DRIVE_ID))
                    val role = it.getString(it.getColumnIndexOrThrow(COLUMN_ROLE))
                    val companyName = it.getString(it.getColumnIndexOrThrow(COLUMN_COMPANY_NAME))
                    val location = it.getString(it.getColumnIndexOrThrow(COLUMN_LOCATION))
                    val companyDescription = it.getString(it.getColumnIndexOrThrow(COLUMN_COMPANY_DECSCRIPTION))
                    val fromDate = it.getString(it.getColumnIndexOrThrow(COLUMN_FROM_DATE))
                    val toDate = it.getString(it.getColumnIndexOrThrow(COLUMN_TO_DATE))
                    val jobType = it.getString(it.getColumnIndexOrThrow(COLUMN_JOB_TYPE))
                    val hiringCount = it.getString(it.getColumnIndexOrThrow(COLUMN_HIRING_COUNT))
                    val requirements = it.getString(it.getColumnIndexOrThrow(COLUMN_REQUIREMENTS))
                    val requirementsEdu = it.getString(it.getColumnIndexOrThrow(COLUMN_REQUIREMENTS_EDU))
                    val salary = it.getString(it.getColumnIndexOrThrow(COLUMN_SALARY))
                    val experience = it.getString(it.getColumnIndexOrThrow(COLUMN_EXPEREIENCE))
                    val rounds = it.getString(it.getColumnIndexOrThrow(COLUMN_ROUNDS))

                    val campusDrive = CampusDrive(
                        driveId = driveId,
                        role = role,
                        companyName = companyName,
                        location =location,
                        companyDescription =  companyDescription,
                        fromDate = fromDate,
                        toDate = toDate,
                        jobType = jobType,
                        hiringCount = hiringCount,
                        requirements = requirements,
                        requirementsEdu = requirementsEdu,
                        salary = salary,
                        experience = experience,
                        rounds = rounds
                    )

                    drives.add(campusDrive)
                }

            }catch (e : SQLiteException){
                Log.d("drives", e.toString())
            }
        }
        cursor?.close()
        return drives
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Complaints.db"
        const val TABLE_COMPLAINTS = "complaints"
        const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DEPARTMENT = "department"
        const val COLUMN_STATUS = "status"
        const val COLUMN_REPLY = "reply"
        const val COLUMN_IMAGE = "image"
        const val COLUMN_DESCRIPTION = "description"

        //campus drive
        const val CAMPUS_DATABSE_NAME = "CampusDrive.db"
        const val TABLE_CAMPUS_DRIVE = "campusDrive"
        const val COLUMN_DRIVE_ID = "driveId"
        const val COLUMN_ROLE = "role"
        const val COLUMN_COMPANY_NAME = "companyName"
        const val COLUMN_LOCATION = "location"
        const val COLUMN_COMPANY_DECSCRIPTION = "companyDescription"
        const val COLUMN_FROM_DATE = "fromDate"
        const val COLUMN_TO_DATE = "toDate"
        const val COLUMN_JOB_TYPE = "jobType"
        const val COLUMN_HIRING_COUNT = "hiringCount"
        const val COLUMN_REQUIREMENTS = "requirements"
        const val COLUMN_REQUIREMENTS_EDU = "requirementsEdu"
        const val COLUMN_SALARY = "salary"
        const val COLUMN_EXPEREIENCE = "experience"
        const val COLUMN_ROUNDS = "rounds"

        //
        const val APPLIED_STUD_DB_NAME = "AppliedStud.db"
        const val TABLE_APPLIED_STUD = "appliedStud"
        const val COLUMN_STUDENT_ID = "studentId"
        const val COLUMN_CAMPUS_DRIVE_ID = "campusDriveId"
        const val COLUMN_FULL_NAME = "fullName"
        const val COLUMN_DOB = "dob"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_GENDER = "gender"
        const val COLUMN_COURSE = "course"
        const val COLUMN_INSTITUTE = "institute"
        const val COLUMN_UNIVERSITY = "university"
        const val COLUMN_YEAR_OF_PASSING = "yearOfPassing"
        const val COLUMN_PERCENTAGE = "percentage"
        const val COLUMN_PDF = "pdf"



        private const val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_COMPLAINTS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USERNAME TEXT," +
                "$COLUMN_TITLE TEXT," +
                "$COLUMN_DEPARTMENT TEXT," +
                "$COLUMN_STATUS TEXT," +
                "$COLUMN_REPLY TEXT," +
                "$COLUMN_IMAGE BLOB," + // Changed to BLOB type for storing images
                "$COLUMN_DESCRIPTION TEXT)"

        private const val SQL_CREATE_DRIVE = "CREATE TABLE $TABLE_CAMPUS_DRIVE (" +
                "$COLUMN_DRIVE_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_ROLE TEXT," +
                "$COLUMN_COMPANY_NAME TEXT," +
                "$COLUMN_LOCATION TEXT," +
                "$COLUMN_COMPANY_DECSCRIPTION TEXT," +
                "$COLUMN_FROM_DATE TEXT," +
                "$COLUMN_TO_DATE TEXT," +
                "$COLUMN_JOB_TYPE TEXT," +
                "$COLUMN_HIRING_COUNT TEXT," + // Changed to BLOB type for storing images
                "$COLUMN_REQUIREMENTS TEXT," +
                "$COLUMN_REQUIREMENTS_EDU TEXT," +
                "$COLUMN_SALARY TEXT," +
                "$COLUMN_EXPEREIENCE TEXT," +
                "$COLUMN_ROUNDS TEXT)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_COMPLAINTS"

        private const val SQL_DELETE_DRIVE = "DROP TABLE IF EXISTS $TABLE_CAMPUS_DRIVE"

        private const val SQL_CREATE_APPLIED_STUD =  "CREATE TABLE $TABLE_APPLIED_STUD (" +
                "$COLUMN_STUDENT_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_CAMPUS_DRIVE_ID TEXT," +
                "$COLUMN_FULL_NAME TEXT," +
                "$COLUMN_DOB TEXT," +
                "$COLUMN_ADDRESS TEXT," +
                "$COLUMN_PHONE TEXT," +
                "$COLUMN_EMAIL TEXT," +
                "$COLUMN_GENDER TEXT," +
                "$COLUMN_COURSE TEXT," + // Changed to BLOB type for storing images
                "$COLUMN_INSTITUTE TEXT," +
                "$COLUMN_UNIVERSITY TEXT," +
                "$COLUMN_YEAR_OF_PASSING TEXT," +
                "$COLUMN_PERCENTAGE TEXT," +
                "$COLUMN_PDF BLOB)"
    }
}
