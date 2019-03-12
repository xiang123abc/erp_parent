package cn.ecut.utils;

import java.util.Base64;
import java.util.UUID;

/**
 * UUID含义是通用唯一识别码 (Universally Unique Identifier)，
 * 这 是一个软件建构的标准，也是被开源软件基金会的组织应用在分布式计算环境领域的重要部分.
 *
 * UUID 的目的是让分布式系统中的所有元素，都能有唯一的辨识资讯，而不需要透过中央控制端来做辨识资讯的指定.
 *
 * 开源软件基金会 (Open Software Foundation, OSF)
 * 分布式计算环境 (Distributed Computing Environment, DCE)
 *
 */
public class UuidHelper {
	
	public static void main(String[] args) {
		
		String uuid = primitive() ;
		
		System.out.println( "primitive :\t" + uuid );
		
		String base64 = compress( uuid ) ;
		
		System.out.println( "base64 :\t\t" + base64 );
		
		String uncompress = uncompress( base64 ) ;
		System.out.println( "uncompress :\t" + uncompress );
		
		System.out.println( "regular :\t\t" + regular() );
		
	}

    private static final StringBuffer BUFFER = new StringBuffer( 32 );
    private static final String SEPARTOR = "-" ;
    
    private static final Base64.Encoder ENCODER = Base64.getUrlEncoder();
    private static final Base64.Decoder DECODER = Base64.getUrlDecoder() ;

    /**
     * 用 java.utils.UUID 产生一个 36 位长度的 uuid 字符串
     * @return
     */
    public static final String primitive() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 用 java.utils.UUID 产生一个 uuid 字符串，并删除其中的 "-" 号，将所有小写字符变成大写
     * @return 根据
     */
    public static final String regular(){
    	BUFFER.setLength( 0 );
        UUID uuid = UUID.randomUUID();
        BUFFER.append( uuid );
        int index = BUFFER.indexOf( SEPARTOR );
        while( index != -1 ){
            BUFFER.deleteCharAt( index );
            index = BUFFER.indexOf( SEPARTOR );
        }
        return StringHelper.upperCase( BUFFER );
    }
    
    /**
     * 随机产生一个UUID，并根据Base64返回一个22位长度的字符串
     * @return
     */
    public static final String uuidByBase64(){
    	UUID uuid = UUID.randomUUID();
    	return compress( uuid );
    }
    
    /**
     * 使用 Base64 将 uuid 字符串压缩成 22 位长度字符串 
     * @param primitive 需要压缩处理的 UUID 字符串
     * @return 返回使用 Base64 压缩后的字符串
     */
    public static final String compress( String primitive ) {
        UUID uuid = UUID.fromString(primitive);
        return compress(uuid);
    }
    
    /**
     * 根据 UUID 对象产生一个 22 位长度的字符串
     * @param uuid 
     * @return
     */
    public static final String compress( UUID uuid ) {
    	byte[] bytes = new byte[16];
        
        long most = uuid.getMostSignificantBits();
        long least = uuid.getLeastSignificantBits();
        
        longToBytes( most , bytes , 0 );
        longToBytes( least, bytes, 8 );
        
        String str = ENCODER.encodeToString( bytes ) ;
        str = str.substring( 0 ,  str.length() - 2 );
        
        return str ;
    }
    
    /**
     * 将一个由 compress 返回的字符串还原成 原始的 UUID 形式
     * @param compressed
     * @return
     */
    public static final String uncompress( String compressed ) {
    	if( compressed == null || compressed.length() != 22 ){
    		throw new IllegalArgumentException("Invalid uuid!");
    	}
    	
    	byte[] bytes = DECODER.decode( compressed + "==" ) ;
    	long most = bytesToLong( bytes,  0 ) ;
    	long least = bytesToLong( bytes , 8 ) ;
    	UUID uuid = new UUID( most , least );
    	return uuid.toString();
    }
    
    
    /**
     * 将一个 long 类型的数值转换为相应的 byte 数组
     * @param value
     * @param bytes
     * @param offset
     */
    protected static void longToBytes(long value, byte[] bytes, int offset) {
        for (int i = 7; i > -1; i--) {
            bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
        }
    }
    
    /**
     * 将一个 byte 数组 转换为一个 long 类型的数值
     * @param bytes
     * @param offset
     * @return
     */
    protected static long bytesToLong(byte[] bytes, int offset) {
        long value = 0;
        for (int i = 7; i > -1; i--) {
            value |= (((long) bytes[offset++]) & 0xFF) << 8 * i;
        }
        return value;
    }

}
