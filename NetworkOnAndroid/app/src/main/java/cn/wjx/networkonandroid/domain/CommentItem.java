package cn.wjx.networkonandroid.domain;

/**
 * @author WuChangJian
 * @date 2020/4/21 14:42
 */
public class CommentItem {

    private String articleId;
    private String commentContent;

    public CommentItem() {
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public CommentItem(String articleId, String commentContent) {
        this.articleId = articleId;
        this.commentContent = commentContent;
    }
}
