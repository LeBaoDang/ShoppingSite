package com.store.util;

public class Constant {
    public static final int PAGE_PRODUCT_MAX_SIZE = 8;
    public static final int PAGE_PRODUCT_CATEGORY_MAX_SIZE = 8;
    // không cho new hàm tạo 
 	// chỉ cho sử dụng qua class không cho new qua đối tượng
 	//( tránh trường hợp new lung tung tiêu hao bộ nhớ)
 	private Constant() {}
    
}
 