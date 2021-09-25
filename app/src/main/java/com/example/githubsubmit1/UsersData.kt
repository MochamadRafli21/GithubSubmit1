package com.example.githubsubmit1

class UsersData {
    private val usersFullName = arrayOf(
            "Jake Wharton",
            "Amit Shekhar",
            "Romain Guy",
            "Chris Banes",
            "David",
            "Ravi Tamada",
            "Deny Prasetyo",
            "Budi Oktaviyan",
            "Hendi Santika",
            "Sidiq Permana"
    )

    private val usersname = arrayOf(
            "JakeWharton",
            "amitshekhariitbhu",
            "romainguy",
            "chrisbanes",
            "tipsy",
            "ravi8x",
            "jasoet",
            "budioktaviyan",
            "hendisantika",
            "sidiqpermana"
    )

    private var usersFollower = arrayOf(
            56995,
            5153,
            7972,
            14725,
            788,
            18628,
            277,
            178,
            428,
            465
    )

    private val Following = arrayOf(
            12,
            2,
            0,
            1,
            0,
            3,
            39,
            23,
            61,
            10
    )

    private val repository = arrayOf(
            102,
            37,
            9,
            30,
            56,
            28,
            44,
            110,
            1064,
            65
    )

    private val company = arrayOf(
            "Google, Inc.",
            "MindOrksOpenSource",
            "Google",
            "Google working on @android",
            "Working Group Two",
            "AndroidHive | Droid5",
            "gojek-engineering",
            "KotlinID",
            "JVMDeveloperID @KotlinID @IDDevOps",
            "Nusantara Beta Studio"
    )

    private val profilePic = arrayOf(
            R.drawable.user1,
            R.drawable.user2,
            R.drawable.user3,
            R.drawable.user4,
            R.drawable.user5,
            R.drawable.user6,
            R.drawable.user7,
            R.drawable.user8,
            R.drawable.user9,
            R.drawable.user10,
    )

    private val city = arrayOf(
            "Pittsburgh, PA, USA",
            "New Delhi, India",
            "California",
            "Sydney, Australia",
            "Trondheim, Norway",
            "India",
            "Kotagede, Yogyakarta, Indonesia",
            "Jakarta, Indonesia",
            "Bojongsoang - Bandung Jawa Barat",
            "Jakarta Indonesia"
    )

    val listData: ArrayList<Users>
        get() {
            val list = arrayListOf<Users>()
            for (i in this.usersFullName.indices){
                val user = Users()
                user.Name = usersFullName[i]
                user.Username = usersname[i]
                user.profilePic =profilePic[i]
                user.Follower = usersFollower[i]
                user.Following = Following[i]
                user.Repository = repository[i]
                user.Company = company[i]
                user.City = city[i]
                list.add(user)
            }
            return list
        }
}