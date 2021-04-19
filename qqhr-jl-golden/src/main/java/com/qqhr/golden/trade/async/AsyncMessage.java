package com.qqhr.golden.trade.async;

import com.qqhr.common.base.BaseObject;

/**
 * @Author WilliamDragon
 * @Date 2021/3/17 16:14
 * @Version 1.0
 */

public class AsyncMessage extends BaseObject {
    private AsyncMessageHead asyncMessageHead;
    private AsyncMessageBody asyncMessageBody;

    public AsyncMessageHead getAsyncMessageHead() {
        return asyncMessageHead;
    }

    public void setAsyncMessageHead(AsyncMessageHead asyncMessageHead) {
        this.asyncMessageHead = asyncMessageHead;
    }

    public AsyncMessageBody getAsyncMessageBody() {
        return asyncMessageBody;
    }

    public void setAsyncMessageBody(AsyncMessageBody asyncMessageBody) {
        this.asyncMessageBody = asyncMessageBody;
    }
}
