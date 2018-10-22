package cn.blog.entity;


import java.io.Serializable;
import java.util.Date;


public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    private Integer userId;
    /**
     * 用户编码(登录账户) 手机号 邮箱号
     */
    private String loginCode;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 身份证
     */
    private String identityCard;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 状态:0注册新用户 1邮件认证用户 2管理员 3黑名单
     */
    private Integer status;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

 
    @Override
    public String toString() {
        return "User{" +
        "userId=" + userId +
        ", loginCode=" + loginCode +
        ", userName=" + userName +
        ", password=" + password +
        ", sex=" + sex +
        ", identityCard=" + identityCard +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        ", status=" + status +
        "}";
    }
}
