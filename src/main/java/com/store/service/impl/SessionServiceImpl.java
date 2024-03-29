package com.store.service.impl;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	HttpSession session;

	/**
	 * Đọc giá trị của attribute trong session
	 * 
	 * @param name tên attribute
	 * @return giá trị đọc được hoặc null nếu không tồn tại
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T) session.getAttribute(name);
	}
	
	/**
	 * Đọc giá trị của attribute trong session
	 * 
	 * @param name tên attribute
	 * @param defaultValue giá trị mặc định
	 * @return giá trị đọc được hoặc giá trị mặc định nếu không tồn tại
	 */
	@Override
	public <T> T get(String name, T defaultValue) {
		T value = get(name);
		return value != null ? value : defaultValue;
	}

	/**
	 * Thay đổi hoặc tạo mới attribute trong session
	 * 
	 * @param name  tên attribute
	 * @param value giá trị attribute
	 */
	@Override
	public void set(String name, Object value) {
		session.setAttribute(name, value);
	}

	/**
	 * Xóa attribute trong session
	 * 
	 * @param name tên attribute cần xóa
	 */
	@Override
	public void remove(String name) {
		session.removeAttribute(name);
	}
}
