abstract class Attachment(val type: String) {

    val attachments = mutableListOf<Attachment>()

    class Video(val length: Int, val size: Int, type: String): Attachment(type) {

    }

    class Audio(val length: Int, val size: Int, type: String): Attachment(type) {

    }

    class Picture(val size: Int, type: String): Attachment(type) {

    }

    class Graffiti(val size: Int, type: String): Attachment(type) {

    }

    class Document(val link: String, val size: Int, type: String): Attachment(type) {

    }
}