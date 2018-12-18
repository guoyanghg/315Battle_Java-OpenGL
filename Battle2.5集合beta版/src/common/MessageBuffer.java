package common;

import java.io.UTFDataFormatException;


public class MessageBuffer {
        private final byte[] buf;

        private final int capacity;


        private int pos = 0;


        private int limit;


        public MessageBuffer(int capacity) {
                this(new byte[capacity]);
                if (capacity == 0) {
                        throw new IllegalArgumentException(
                                        "capacity must be greater than 0");
                }
                this.limit = 1;
        }


        public MessageBuffer(byte[] buf) {
                this.buf = buf;
                this.capacity = buf.length;
                this.limit = buf.length;
        }


        public int capacity() {
                return capacity;
        }


        public int limit() {
                return limit;
        }


        public int position() {
                return pos;
        }


        public void rewind() {
                pos = 0;
        }


        public MessageBuffer putByte(int b) {
                if (pos == capacity) {
                        throw new IndexOutOfBoundsException();
                }
                buf[pos++] = (byte) b;
                limit = (pos == capacity ? pos : pos + 1);
                return this;
        }


        public MessageBuffer putByteArray(byte[] bytes) {
                if (pos + 2 + bytes.length > capacity) {
                        throw new IndexOutOfBoundsException();
                }
                putShort(bytes.length);
                putBytes(bytes);
                return this;
        }


        public MessageBuffer putBytes(byte[] bytes) {
                if (pos + bytes.length > capacity) {
                        throw new IndexOutOfBoundsException();
                }
                for (byte b : bytes) {
                        putByte(b);
                }
                return this;
        }


        public MessageBuffer putChar(int v) {
                if (pos + 2 > capacity) {
                        throw new IndexOutOfBoundsException();
                }
                putByte((v >>> 8) & 0xFF);
                putByte((v >>> 0) & 0xFF);
                return this;
        }


        public MessageBuffer putShort(int v) {
                if (pos + 2 > capacity) {
                        throw new IndexOutOfBoundsException();
                }
                putByte((v >>> 8) & 0xFF);
                putByte((v >>> 0) & 0xFF);
                return this;
        }


        public MessageBuffer putInt(int v) {
                if (pos + 4 > capacity) {
                        throw new IndexOutOfBoundsException();
                }
                putByte((v >>> 24) & 0xff);
                putByte((v >>> 16) & 0xff);
                putByte((v >>> 8) & 0xff);
                putByte((v >>> 0) & 0xff);
                return this;
        }


        public MessageBuffer putLong(long v) {
                if (pos + 8 > capacity) {
                        throw new IndexOutOfBoundsException();
                }
                putByte((byte) (v >>> 56));
                putByte((byte) (v >>> 48));
                putByte((byte) (v >>> 40));
                putByte((byte) (v >>> 32));
                putByte((byte) (v >>> 24));
                putByte((byte) (v >>> 16));
                putByte((byte) (v >>> 8));
                putByte((byte) (v >>> 0));
                return this;
        }


        public MessageBuffer putString(String str) {
                int size = getSize(str);
                if (pos + size > capacity) {
                        throw new IndexOutOfBoundsException();
                }


                putShort(size - 2);


                int strlen = str.length();
                int i = 0;


                for (i = 0; i < strlen; i++) {
                        char c = str.charAt(i);
                        if (!((c >= 0x0001) && (c <= 0x007F))) {
                                break;
                        }
                        buf[pos++] = (byte) c;
                }


                for (; i < strlen; i++) {
                        char c = str.charAt(i);
                        if ((c >= 0x0001) && (c <= 0x007F)) {
                                buf[pos++] = (byte) c;


                        } else if (c > 0x07FF) {
                                buf[pos++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
                                buf[pos++] = (byte) (0x80 | ((c >> 6) & 0x3F));
                                buf[pos++] = (byte) (0x80 | ((c >> 0) & 0x3F));
                        } else {
                                buf[pos++] = (byte) (0xC0 | ((c >> 6) & 0x1F));
                                buf[pos++] = (byte) (0x80 | ((c >> 0) & 0x3F));
                        }
                }
                limit = (pos == capacity ? pos : pos + 1);


                return this;
        }


        public byte getByte() {
                if (pos == limit) {
                        throw new IndexOutOfBoundsException();
                }
                byte b = buf[pos++];
                return b;
        }


        public byte[] getByteArray() {
                int savePos = pos;
                try {
                        return getBytes(getUnsignedShort());
                } catch (IndexOutOfBoundsException e) {
                        pos = savePos;
                        throw e;
                }
        }


        public byte[] getBytes(int size) {
                if (pos + size > limit) {
                        throw new IndexOutOfBoundsException();
                }


                byte[] bytes = new byte[size];
                for (int i = 0; i < size; i++) {
                        bytes[i] = getByte();
                }
                return bytes;
        }


        public short getShort() {
                if (pos + 2 > limit) {
                        throw new IndexOutOfBoundsException();
                }


                return (short) ((getByte() << 8) + (getByte() & 255));
        }


        public int getUnsignedShort() {
                if (pos + 2 > limit) {
                        throw new IndexOutOfBoundsException();
                }


                return ((getByte() & 255) << 8) + ((getByte() & 255) << 0);
        }


        public int getInt() {
                if (pos + 4 > limit) {
                        throw new IndexOutOfBoundsException();
                }


                return ((getByte() & 255) << 24) + ((getByte() & 255) << 16)
                                + ((getByte() & 255) << 8) + ((getByte() & 255) << 0);
        }


        public long getLong() {
                if (pos + 8 > limit) {
                        throw new IndexOutOfBoundsException();
                }


                return ((long) (getByte() & 255) << 56)
                                + ((long) (getByte() & 255) << 48)
                                + ((long) (getByte() & 255) << 40)
                                + ((long) (getByte() & 255) << 32)
                                + ((long) (getByte() & 255) << 24)
                                + ((long) (getByte() & 255) << 16)
                                + ((long) (getByte() & 255) << 8)
                                + ((long) (getByte() & 255) << 0);
        }


        public char getChar() {
                if (pos + 2 > limit) {
                        throw new IndexOutOfBoundsException();
                }


                return (char) ((getByte() << 8) + (getByte() & 255));
        }


        public String getString() {
                if (pos + 2 > limit) {
                        throw new IndexOutOfBoundsException();
                }


                int savePos = pos;
                int utfLen = getUnsignedShort();
                int utfEnd = utfLen + pos;
                if (utfEnd > limit) {
                        pos = savePos;
                        throw new IndexOutOfBoundsException();
                }


                char[] chars = new char[utfLen * 2];
                int c, char2, char3;
                int index = 0;


                while (pos < utfEnd) {
                        c = buf[pos] & 0xff;
                        if (c > 127) {
                                break;
                        }
                        pos++;
                        chars[index++] = (char) c;
                }


                try {
                        while (pos < utfEnd) {
                                c = buf[pos] & 0xff;


                                switch (c >> 4) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                        pos++;
                                        chars[index++] = (char) c;
                                        break;
                                case 12:
                                case 13:
                                        pos += 2;
                                        if (pos > utfEnd) {
                                                throw new UTFDataFormatException(
                                                                "malformed input: partial character at end");
                                        }
                                        char2 = buf[pos - 1];
                                        if ((char2 & 0xC0) != 0x80) {
                                                throw new UTFDataFormatException(
                                                                "malformed input around byte " + pos);
                                        }
                                        chars[index++] = (char) (((c & 0x1F) << 6) | (char2 & 0x3F));
                                        break;
                                case 14:
                                        pos += 3;
                                        if (pos > utfEnd) {
                                                throw new UTFDataFormatException(
                                                                "malformed input: partial character at end");
                                        }
                                        char2 = buf[pos - 2];
                                        char3 = buf[pos - 1];
                                        if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80)) {
                                                throw new UTFDataFormatException(
                                                                "malformed input around byte " + (pos - 1));
                                        }
                                        chars[index++] = (char) (((c & 0x0F) << 12)
                                                        | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
                                        break;
                                default:
                                        throw new UTFDataFormatException(
                                                        "malformed input around byte " + pos);
                                }
                        }


                } catch (UTFDataFormatException e) {
                        pos = savePos;
                        throw (RuntimeException) (new RuntimeException()).initCause(e);
                }


                return new String(chars, 0, index);
        }


        public byte[] getBuffer() {
                return buf;
        }


        public static int getSize(String str) {
                int utfLen = 0;
                for (int i = 0; i < str.length(); i++) {
                        int c = str.charAt(i);
                        if ((c >= 0x0001) && (c <= 0x007F)) {
                                utfLen++;
                        } else if (c > 0x07FF) {
                                utfLen += 3;
                        } else {
                                utfLen += 2;
                        }
                }
                return utfLen + 2;
        }



}
