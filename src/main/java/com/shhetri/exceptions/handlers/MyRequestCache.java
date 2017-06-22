package com.shhetri.exceptions.handlers;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Component;

@Component("myRequestCache")
public class MyRequestCache extends HttpSessionRequestCache {
    public MyRequestCache() {
        super();
    }
}
