package com.tuan.exercise.sprdict.constant;

import java.util.Map;

public class Page {
    private Page() {
    }

    public static final String INDEX = "index";
    public static final String AUTHEN = "authen";
    public static final String WORD_INPUT = "word-input";

    public static class Direct {
        private Direct() {
        }

        public static String getRedirect(String route, Map<String, Object> paramMap) {

            StringBuilder sb = new StringBuilder("redirect:");
            sb.append(route);

            if (paramMap != null && paramMap.size() > 0) {
                sb.append("?");

                int i = 0;
                for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                    sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());

                    if (i++ < paramMap.size() - 1)
                        sb.append("&");
                }
            }

            return sb.toString();
        }
    }
}
