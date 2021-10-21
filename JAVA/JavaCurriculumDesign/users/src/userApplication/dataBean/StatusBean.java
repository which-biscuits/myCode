package userApplication.dataBean;

public enum StatusBean {
    OnDiscovery,            // 正在 发现中心 工作区
    OnService,              // 正在 客服通信 工作区
    OnDistribution,         // 正在 发布中心 工作区
    OnMessage,              // 正在 用户通信 工作区
    OnMyDistribution,       // 正在 我的发布 工作区
    OnMyPurchases,          // 正在 我的购买 工作区
    OnPersonalData,         // 正在 用户个人数据 工作区
    OnMessageWithSeller,    // 正在 用户通信工作区 并请求打开每个用户的聊天框
    OnIntroduction          // 正在 商品介绍 工作区
}
