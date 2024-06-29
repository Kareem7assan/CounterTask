package com.rowaad.app.usecase

//import com.rowaad.app.data.BuildConfig


object Consts {

    object ERROR_API {
        //deleted or unauthorized 401
        const val UNAUTHRIZED = "unAuthroized"
        const val FORBIDDEN = "Forbidden"

        //500
        const val SERVER_ERROR = "serverError"

        //503
        const val MAINTENANCE = "maintenanceMode"

        //400
        const val BODY_ERROR = "body_error"

        //400
        const val BAD_REQUEST = "bad_request"

        //404
        const val NOT_FOUND = "not_found"

        const val USER_DELETED = "userDelete"

        //no internet connection
        const val CONNECTION_ERROR = "connection_error"
        //no items in DB
        const val DB_ERROR = "no items provided"
        const val ERROR_FORMAT = "There is some format error"


    }
    val EMOJI =
        // Zero Width Joiners need to be ahead of other Emoji to get priority
        "(?:\ud83d\udc68\ud83c\udffb\u200d\ud83e\udd1d\u200d\ud83d\udc68[\ud83c\udffc-\ud83c\udfff]" +
                "|\ud83d\udc68\ud83c\udffc\u200d\ud83e\udd1d\u200d\ud83d\udc68[\ud83c\udffb\ud83c\udffd-" +
                "\ud83c\udfff]|\ud83d\udc68\ud83c\udffd\u200d\ud83e\udd1d\u200d\ud83d\udc68[\ud83c\udffb" +
                "\ud83c\udffc\ud83c\udffe\ud83c\udfff]" +
                "|\ud83d\udc68\ud83c\udfff\u200d\ud83e" +
                "\udd1d\u200d\ud83d\udc68[\ud83c\udffb-\ud83c\udffe]|\ud83d\udc69\ud83c\udffb\u200d\ud83e" +
                "\udd1d\u200d\ud83d\udc68[\ud83c\udffc-\ud83c\udfff]|\ud83d\udc69\ud83c\udffb\u200d\ud83e" +
                "\udd1d\u200d\ud83d\udc69[\ud83c\udffc-\ud83c\udfff]|\ud83d\udc69\ud83c\udffc\u200d\ud83e" +
                "\udd1d\u200d\ud83d\udc68[\ud83c\udffb\ud83c\udffd-\ud83c\udfff]|\ud83d\udc69\ud83c\udffc" +
                "\u200d\ud83e\udd1d\u200d\ud83d\udc69[\ud83c\udffb\ud83c\udffd-\ud83c\udfff]|\ud83d\udc69" +
                "\ud83c\udffd\u200d\ud83e\udd1d\u200d\ud83d\udc68[\ud83c\udffb\ud83c\udffc\ud83c\udffe" +
                "\ud83c\udfff]|\ud83d\udc69\ud83c\udffd\u200d\ud83e\udd1d\u200d\ud83d\udc69[\ud83c\udffb" +
                "\ud83c\udffc\ud83c\udffe\ud83c\udfff]|\ud83d\udc69\ud83c\udffe\u200d\ud83e\udd1d\u200d" +
                "\ud83d\udc68[\ud83c\udffb-\ud83c\udffd\ud83c\udfff]|\ud83d\udc69\ud83c\udffe\u200d\ud83e" +
                "\udd1d\u200d\ud83d\udc69[\ud83c\udffb-\ud83c\udffd\ud83c\udfff]|\ud83d\udc69\ud83c\udfff" +
                "\u200d\ud83e\udd1d\u200d\ud83d\udc68[\ud83c\udffb-\ud83c\udffe]|\ud83d\udc69\ud83c\udfff" +
                "\u200d\ud83e\udd1d\u200d\ud83d\udc69[\ud83c\udffb-\ud83c\udffe]|\ud83e\uddd1\ud83c\udffb" +
                "\u200d\ud83e\udd1d\u200d\ud83e\uddd1[\ud83c\udffb-\ud83c\udfff]|\ud83e\uddd1\ud83c\udffc" +
                "\u200d\ud83e\udd1d\u200d\ud83e\uddd1[\ud83c\udffb-\ud83c\udfff]|\ud83e\uddd1\ud83c\udffd" +
                "\u200d\ud83e\udd1d\u200d\ud83e\uddd1[\ud83c\udffb-\ud83c\udfff]|\ud83e\uddd1\ud83c\udffe" +
                "\u200d\ud83e\udd1d\u200d\ud83e\uddd1[\ud83c\udffb-\ud83c\udfff]|\ud83e\uddd1\ud83c\udfff" +
                "\u200d\ud83e\udd1d\u200d\ud83e\uddd1[\ud83c\udffb-\ud83c\udfff]|\ud83e\uddd1\u200d\ud83e" +
                "\udd1d\u200d\ud83e\uddd1|\ud83d\udc6b[\ud83c\udffb-\ud83c\udfff]|\ud83d\udc6c[\ud83c\udffb" +
                "-\ud83c\udfff]|\ud83d\udc6d[\ud83c\udffb-\ud83c\udfff]|[\ud83d\udc6b-\ud83d\udc6d])" + "|" +

                // Leading woman/man zwj with optional skin tone
                "[\ud83d\udc68\ud83d\udc69\ud83e\uddd1][\ud83c\udffb-\ud83c\udfff]?\u200d" +
                "(?:\u2695\ufe0f|\u2696\ufe0f|\u2708\ufe0f|[\ud83c\udf3e\ud83c\udf73\ud83c\udf93\ud83c" +
                "\udfa4\ud83c\udfa8\ud83c\udfeb\ud83c\udfed\ud83d\udcbb\ud83d\udcbc\ud83d\udd27\ud83d\udd2c" +
                "\ud83d\ude80\ud83d\ude92\ud83e\uddaf-\ud83e\uddb3\ud83e\uddbc\ud83e\uddbd])" + "|" +

                // Variant or skin tone before trailing female/male zwj (+ a captured group)
                // The group provides a way to detect that the base has VS16 instead of skin tone
                "[\u26f9\ud83c\udfcb\ud83c\udfcc\ud83d\udd74\ud83d\udd75]" +
                "([\ufe0f\ud83c\udffb-\ud83c\udfff]\u200d[\u2640\u2642]\ufe0f)|" +

                // Optional skin tone before trailing female/male zwj
                "[\ud83c\udfc3\ud83c\udfc4\ud83c\udfca\ud83d\udc6e\ud83d\udc71\ud83d\udc73\ud83d\udc77" +
                "\ud83d\udc81\ud83d\udc82\ud83d\udc86\ud83d\udc87\ud83d\ude45-\ud83d\ude47\ud83d\ude4b" +
                "\ud83d\ude4d\ud83d\ude4e\ud83d\udea3\ud83d\udeb4-\ud83d\udeb6\ud83e\udd26\ud83e\udd35" +
                "\ud83e\udd37-\ud83e\udd39\ud83e\udd3d\ud83e\udd3e\ud83e\uddb8\ud83e\uddb9\ud83e\uddcd-" +
                "\ud83e\uddcf\ud83e\uddd6-\ud83e\udddd]" +
                "[\ud83c\udffb-\ud83c\udfff]?\u200d[\u2640\u2642]\ufe0f|" +

                // Other zwj sequences
                "(?:\ud83d\udc68\u200d\u2764\ufe0f\u200d\ud83d\udc8b\u200d\ud83d\udc68|\ud83d\udc69\u200d" +
                "\u2764\ufe0f\u200d\ud83d\udc8b\u200d[\ud83d\udc68\ud83d\udc69]|\ud83d\udc68\u200d\ud83d" +
                "\udc68\u200d\ud83d\udc66\u200d\ud83d\udc66|\ud83d\udc68\u200d\ud83d\udc68\u200d\ud83d" +
                "\udc67\u200d[\ud83d\udc66\ud83d\udc67]|\ud83d\udc68\u200d\ud83d\udc69\u200d\ud83d\udc66" +
                "\u200d\ud83d\udc66|\ud83d\udc68\u200d\ud83d\udc69\u200d\ud83d\udc67\u200d[\ud83d\udc66" +
                "\ud83d\udc67]|\ud83d\udc69\u200d\ud83d\udc69\u200d\ud83d\udc66\u200d\ud83d\udc66|\ud83d" +
                "\udc69\u200d\ud83d\udc69\u200d\ud83d\udc67\u200d[\ud83d\udc66\ud83d\udc67]|\ud83d\udc68" +
                "\u200d\u2764\ufe0f\u200d\ud83d\udc68|\ud83d\udc69\u200d\u2764\ufe0f\u200d[\ud83d\udc68" +
                "\ud83d\udc69]|\ud83c\udff3\ufe0f\u200d\u26a7\ufe0f|\ud83d\udc68\u200d\ud83d\udc66\u200d" +
                "\ud83d\udc66|\ud83d\udc68\u200d\ud83d\udc67\u200d[\ud83d\udc66\ud83d\udc67]|\ud83d\udc68" +
                "\u200d\ud83d\udc68\u200d[\ud83d\udc66\ud83d\udc67]|\ud83d\udc68\u200d\ud83d\udc69\u200d[" +
                "\ud83d\udc66\ud83d\udc67]|\ud83d\udc69\u200d\ud83d\udc66\u200d\ud83d\udc66|\ud83d\udc69" +
                "\u200d\ud83d\udc67\u200d[\ud83d\udc66\ud83d\udc67]|\ud83d\udc69\u200d\ud83d\udc69\u200d[" +
                "\ud83d\udc66\ud83d\udc67]|\ud83c\udff3\ufe0f\u200d\ud83c\udf08|\ud83c\udff4\u200d\u2620" +
                "\ufe0f|\ud83d\udc6f\u200d\u2640\ufe0f|\ud83d\udc6f\u200d\u2642\ufe0f|\ud83e\udd3c\u200d" +
                "\u2640\ufe0f|\ud83e\udd3c\u200d\u2642\ufe0f|\ud83e\uddde\u200d\u2640\ufe0f|\ud83e\uddde" +
                "\u200d\u2642\ufe0f|\ud83e\udddf\u200d\u2640\ufe0f|\ud83e\udddf\u200d\u2642\ufe0f|\ud83d" +
                "\udc15\u200d\ud83e\uddba|\ud83d\udc41\u200d\ud83d\udde8|\ud83d\udc68\u200d[\ud83d\udc66" +
                "\ud83d\udc67]|\ud83d\udc69\u200d[\ud83d\udc66\ud83d\udc67])" + "|" +

                // Emojified symbols #, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 + possible variant selector + keycap
                "[#*0-9]" +
                "\ufe0f?\u20e3|" +

                // Emoji which default to text must be followed by U+fe0f
                "(?:[©®\u2122\u265f]\ufe0f)|" +

                // Variants that may be followed by U+fe0f but cannot be followed by U+fe0e
                "[\u203c\u2049\u2139\u2194-\u2199\u21a9\u21aa\u231a\u231b\u2328\u23cf\u23ed-\u23ef\u23f1" +
                "\u23f2\u23f8-\u23fa\u24c2\u25aa\u25ab\u25b6\u25c0\u25fb-\u25fe\u2600-\u2604\u260e\u2611" +
                "\u2614\u2615\u2618\u2620\u2622\u2623\u2626\u262a\u262e\u262f\u2638-\u263a\u2640\u2642" +
                "\u2648-\u2653\u2660\u2663\u2665\u2666\u2668\u267b\u267f\u2692-\u2697\u2699\u269b\u269c" +
                "\u26a0\u26a1\u26a7\u26aa\u26ab\u26b0\u26b1\u26bd\u26be\u26c4\u26c5\u26c8\u26cf\u26d1\u26d3" +
                "\u26d4\u26e9\u26ea\u26f0-\u26f5\u26f8\u26fa\u26fd\u2702\u2708\u2709\u270f\u2712\u2714" +
                "\u2716\u271d\u2721\u2733\u2734\u2744\u2747\u2757\u2763\u2764\u27a1\u2934\u2935\u2b05-" +
                "\u2b07\u2b1b\u2b1c\u2b50\u2b55\u3030\u303d\u3297\u3299\ud83c\udc04\ud83c\udd70\ud83c\udd71" +
                "\ud83c\udd7e\ud83c\udd7f\ud83c\ude02\ud83c\ude1a\ud83c\ude2f\ud83c\ude37\ud83c\udf21\ud83c" +
                "\udf24-\ud83c\udf2c\ud83c\udf36\ud83c\udf7d\ud83c\udf96\ud83c\udf97\ud83c\udf99-\ud83c" +
                "\udf9b\ud83c\udf9e\ud83c\udf9f\ud83c\udfcd\ud83c\udfce\ud83c\udfd4-\ud83c\udfdf\ud83c" +
                "\udff3\ud83c\udff5\ud83c\udff7\ud83d\udc3f\ud83d\udc41\ud83d\udcfd\ud83d\udd49\ud83d\udd4a" +
                "\ud83d\udd6f\ud83d\udd70\ud83d\udd73\ud83d\udd76-\ud83d\udd79\ud83d\udd87\ud83d\udd8a-" +
                "\ud83d\udd8d\ud83d\udda5\ud83d\udda8\ud83d\uddb1\ud83d\uddb2\ud83d\uddbc\ud83d\uddc2-" +
                "\ud83d\uddc4\ud83d\uddd1-\ud83d\uddd3\ud83d\udddc-\ud83d\uddde\ud83d\udde1\ud83d\udde3" +
                "\ud83d\udde8\ud83d\uddef\ud83d\uddf3\ud83d\uddfa\ud83d\udecb\ud83d\udecd-\ud83d\udecf" +
                "\ud83d\udee0-\ud83d\udee5\ud83d\udee9\ud83d\udef0\ud83d\udef3]" +
                "(?:\ufe0f|(?!\ufe0e))|" +

                // Diversity Emoji followed by optional skin tone
                "(?:" +
                // Diversity variants that may be followed by U+fe0f but cannot be followed by U+fe0e
                "[\u261d\u26f7\u26f9\u270c\u270d\ud83c\udfcb\ud83c\udfcc\ud83d\udd74\ud83d\udd75\ud83d" +
                "\udd90]" +
                "(?:\ufe0f|(?!\ufe0e))|" +

                // Diversity non-variants
                "[\u270a\u270b\ud83c\udf85\ud83c\udfc2-\ud83c\udfc4\ud83c\udfc7\ud83c\udfca\ud83d\udc42" +
                "\ud83d\udc43\ud83d\udc46-\ud83d\udc50\ud83d\udc66-\ud83d\udc69\ud83d\udc6e\ud83d\udc70-" +
                "\ud83d\udc78\ud83d\udc7c\ud83d\udc81-\ud83d\udc83\ud83d\udc85-\ud83d\udc87\ud83d\udcaa" +
                "\ud83d\udd7a\ud83d\udd95\ud83d\udd96\ud83d\ude45-\ud83d\ude47\ud83d\ude4b-\ud83d\ude4f" +
                "\ud83d\udea3\ud83d\udeb4-\ud83d\udeb6\ud83d\udec0\ud83d\udecc\ud83e\udd0f\ud83e\udd18-" +
                "\ud83e\udd1c\ud83e\udd1e\ud83e\udd1f\ud83e\udd26\ud83e\udd30-\ud83e\udd39\ud83e\udd3d" +
                "\ud83e\udd3e\ud83e\uddb5\ud83e\uddb6\ud83e\uddb8\ud83e\uddb9\ud83e\uddbb\ud83e\uddcd-" +
                "\ud83e\uddcf\ud83e\uddd1-\ud83e\udddd]" +
                ")[\ud83c\udffb-\ud83c\udfff]?|" +

                // Flags, Regional Symbols, and Normal Emoji
                "(?:\ud83c\udff4\udb40\udc67\udb40\udc62\udb40\udc65\udb40\udc6e\udb40\udc67\udb40\udc7f|" +
                "\ud83c\udff4\udb40\udc67\udb40\udc62\udb40\udc73\udb40\udc63\udb40\udc74\udb40\udc7f|" +
                "\ud83c\udff4\udb40\udc67\udb40\udc62\udb40\udc77\udb40\udc6c\udb40\udc73\udb40\udc7f|" +
                "\ud83c\udde6[\ud83c\udde8-\ud83c\uddec\ud83c\uddee\ud83c\uddf1\ud83c\uddf2\ud83c\uddf4" +
                "\ud83c\uddf6-\ud83c\uddfa\ud83c\uddfc\ud83c\uddfd\ud83c\uddff]|\ud83c\udde7[\ud83c\udde6" +
                "\ud83c\udde7\ud83c\udde9-\ud83c\uddef\ud83c\uddf1-\ud83c\uddf4\ud83c\uddf6-\ud83c\uddf9" +
                "\ud83c\uddfb\ud83c\uddfc\ud83c\uddfe\ud83c\uddff]|\ud83c\udde8[\ud83c\udde6\ud83c\udde8" +
                "\ud83c\udde9\ud83c\uddeb-\ud83c\uddee\ud83c\uddf0-\ud83c\uddf5\ud83c\uddf7\ud83c\uddfa-" +
                "\ud83c\uddff]|\ud83c\udde9[\ud83c\uddea\ud83c\uddec\ud83c\uddef\ud83c\uddf0\ud83c\uddf2" +
                "\ud83c\uddf4\ud83c\uddff]|\ud83c\uddea[\ud83c\udde6\ud83c\udde8\ud83c\uddea\ud83c\uddec" +
                "\ud83c\udded\ud83c\uddf7-\ud83c\uddfa]|\ud83c\uddeb[\ud83c\uddee-\ud83c\uddf0\ud83c\uddf2" +
                "\ud83c\uddf4\ud83c\uddf7]|\ud83c\uddec[\ud83c\udde6\ud83c\udde7\ud83c\udde9-\ud83c\uddee" +
                "\ud83c\uddf1-\ud83c\uddf3\ud83c\uddf5-\ud83c\uddfa\ud83c\uddfc\ud83c\uddfe]|\ud83c\udded[" +
                "\ud83c\uddf0\ud83c\uddf2\ud83c\uddf3\ud83c\uddf7\ud83c\uddf9\ud83c\uddfa]|\ud83c\uddee[" +
                "\ud83c\udde8-\ud83c\uddea\ud83c\uddf1-\ud83c\uddf4\ud83c\uddf6-\ud83c\uddf9]|\ud83c\uddef[" +
                "\ud83c\uddea\ud83c\uddf2\ud83c\uddf4\ud83c\uddf5]|\ud83c\uddf0[\ud83c\uddea\ud83c\uddec-" +
                "\ud83c\uddee\ud83c\uddf2\ud83c\uddf3\ud83c\uddf5\ud83c\uddf7\ud83c\uddfc\ud83c\uddfe\ud83c" +
                "\uddff]|\ud83c\uddf1[\ud83c\udde6-\ud83c\udde8\ud83c\uddee\ud83c\uddf0\ud83c\uddf7-\ud83c" +
                "\uddfb\ud83c\uddfe]|\ud83c\uddf2[\ud83c\udde6\ud83c\udde8-\ud83c\udded\ud83c\uddf0-\ud83c" +
                "\uddff]|\ud83c\uddf3[\ud83c\udde6\ud83c\udde8\ud83c\uddea-\ud83c\uddec\ud83c\uddee\ud83c" +
                "\uddf1\ud83c\uddf4\ud83c\uddf5\ud83c\uddf7\ud83c\uddfa\ud83c\uddff]|\ud83c\uddf4\ud83c" +
                "\uddf2|\ud83c\uddf5[\ud83c\udde6\ud83c\uddea-\ud83c\udded\ud83c\uddf0-\ud83c\uddf3\ud83c" +
                "\uddf7-\ud83c\uddf9\ud83c\uddfc\ud83c\uddfe]|\ud83c\uddf6\ud83c\udde6|\ud83c\uddf7[\ud83c" +
                "\uddea\ud83c\uddf4\ud83c\uddf8\ud83c\uddfa\ud83c\uddfc]|\ud83c\uddf8[\ud83c\udde6-\ud83c" +
                "\uddea\ud83c\uddec-\ud83c\uddf4\ud83c\uddf7-\ud83c\uddf9\ud83c\uddfb\ud83c\uddfd-\ud83c" +
                "\uddff]|\ud83c\uddf9[\ud83c\udde6\ud83c\udde8\ud83c\udde9\ud83c\uddeb-\ud83c\udded\ud83c" +
                "\uddef-\ud83c\uddf4\ud83c\uddf7\ud83c\uddf9\ud83c\uddfb\ud83c\uddfc\ud83c\uddff]|\ud83c" +
                "\uddfa[\ud83c\udde6\ud83c\uddec\ud83c\uddf2\ud83c\uddf3\ud83c\uddf8\ud83c\uddfe\ud83c" +
                "\uddff]|\ud83c\uddfb[\ud83c\udde6\ud83c\udde8\ud83c\uddea\ud83c\uddec\ud83c\uddee\ud83c" +
                "\uddf3\ud83c\uddfa]|\ud83c\uddfc[\ud83c\uddeb\ud83c\uddf8]|\ud83c\uddfd\ud83c\uddf0|\ud83c" +
                "\uddfe[\ud83c\uddea\ud83c\uddf9]|\ud83c\uddff[\ud83c\udde6\ud83c\uddf2\ud83c\uddfc]|[" +
                "\u23e9-\u23ec\u23f0\u23f3\u267e\u26ce\u2705\u2728\u274c\u274e\u2753-\u2755\u2795-\u2797" +
                "\u27b0\u27bf\ue50a\ud83c\udccf\ud83c\udd8e\ud83c\udd91-\ud83c\udd9a\ud83c\udde6-\ud83c" +
                "\uddff\ud83c\ude01\ud83c\ude32-\ud83c\ude36\ud83c\ude38-\ud83c\ude3a\ud83c\ude50\ud83c" +
                "\ude51\ud83c\udf00-\ud83c\udf20\ud83c\udf2d-\ud83c\udf35\ud83c\udf37-\ud83c\udf7c\ud83c" +
                "\udf7e-\ud83c\udf84\ud83c\udf86-\ud83c\udf93\ud83c\udfa0-\ud83c\udfc1\ud83c\udfc5\ud83c" +
                "\udfc6\ud83c\udfc8\ud83c\udfc9\ud83c\udfcf-\ud83c\udfd3\ud83c\udfe0-\ud83c\udff0\ud83c" +
                "\udff4\ud83c\udff8-\ud83d\udc3e\ud83d\udc40\ud83d\udc44\ud83d\udc45\ud83d\udc51-\ud83d" +
                "\udc65\ud83d\udc6a\ud83d\udc6f\ud83d\udc79-\ud83d\udc7b\ud83d\udc7d-\ud83d\udc80\ud83d" +
                "\udc84\ud83d\udc88-\ud83d\udca9\ud83d\udcab-\ud83d\udcfc\ud83d\udcff-\ud83d\udd3d\ud83d" +
                "\udd4b-\ud83d\udd4e\ud83d\udd50-\ud83d\udd67\ud83d\udda4\ud83d\uddfb-\ud83d\ude44\ud83d" +
                "\ude48-\ud83d\ude4a\ud83d\ude80-\ud83d\udea2\ud83d\udea4-\ud83d\udeb3\ud83d\udeb7-\ud83d" +
                "\udebf\ud83d\udec1-\ud83d\udec5\ud83d\uded0-\ud83d\uded2\ud83d\uded5\ud83d\udeeb\ud83d" +
                "\udeec\ud83d\udef4-\ud83d\udefa\ud83d\udfe0-\ud83d\udfeb\ud83e\udd0d\ud83e\udd0e\ud83e" +
                "\udd10-\ud83e\udd17\ud83e\udd1d\ud83e\udd20-\ud83e\udd25\ud83e\udd27-\ud83e\udd2f\ud83e" +
                "\udd3a\ud83e\udd3c\ud83e\udd3f-\ud83e\udd45\ud83e\udd47-\ud83e\udd71\ud83e\udd73-\ud83e" +
                "\udd76\ud83e\udd7a-\ud83e\udda2\ud83e\udda5-\ud83e\uddaa\ud83e\uddae-\ud83e\uddb4\ud83e" +
                "\uddb7\ud83e\uddba\ud83e\uddbc-\ud83e\uddca\ud83e\uddd0\ud83e\uddde-\ud83e\uddff\ud83e" +
                "\ude70-\ud83e\ude73\ud83e\ude78-\ud83e\ude7a\ud83e\ude80-\ud83e\ude82\ud83e\ude90-\ud83e" +
                "\ude95])" + "|" +"\ufe0f"
    object INTENT {
        const val CURRENT = "current"
        const val ORDER_ID = "orderId"
        const val ROOM = "room"
        const val MSG = "msg"
        const val USER = "user"
        const val LOGOUT = "logout"
        const val PHONE = "phone"
        const val ORDER_KEY = "order"
        const val MSG_KEY = "message_key"
        const val INTENT_IMG = "img"

        const val NAV = "out_nav"
    }

    object ORDER {
        const val ACCEPTED = "accepted"
        const val ON_WAY_TO_CUSTOMER = "deliveryOnTheWay"
        const val PENDING = "pending"
        const val COMPLETED = "delivered"
        const val SHIPPED = "shipped"
        const val CANCELLED = "canceledd"
    }

    object FCM {
        const val NEW_ORDER = "order"
        const val PULL_ORDER = "pullOrder"
        const val CANCEL_ORDER = "cancelOrder"
        const val ACCEPT_ORDER_ADMIN = "acceptedOrder"
        const val ON_WAY_ADMIN = "deliveryOnTheWayOrder"
        const val COMPLETE_ADMIN = "completedOrder"
        const val NOT_COMPLETE_ADMIN = "notCompletedOrder"

    }

    const val INTENT_EXTRA = "extra"

    const val DEFAULT_APP_LANGUAGE = "ar"

}