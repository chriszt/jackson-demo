package pkg.tree;

import com.fasterxml.jackson.core.*;
import pkg.bean.User;

import java.util.Iterator;

public class UserTreeNode implements TreeNode {

    private User user;

    public UserTreeNode(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public JsonToken asToken() {
        return null;
    }

    @Override
    public JsonParser.NumberType numberType() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isValueNode() {
        return false;
    }

    @Override
    public boolean isContainerNode() {
        return false;
    }

    @Override
    public boolean isMissingNode() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isObject() {
        return false;
    }

    @Override
    public TreeNode get(String fieldName) {
        return null;
    }

    @Override
    public TreeNode get(int index) {
        return null;
    }

    @Override
    public TreeNode path(String fieldName) {
        return null;
    }

    @Override
    public TreeNode path(int index) {
        return null;
    }

    @Override
    public Iterator<String> fieldNames() {
        return null;
    }

    @Override
    public TreeNode at(JsonPointer ptr) {
        return null;
    }

    @Override
    public TreeNode at(String jsonPointerExpression) throws IllegalArgumentException {
        return null;
    }

    @Override
    public JsonParser traverse() {
        return null;
    }

    @Override
    public JsonParser traverse(ObjectCodec codec) {
        return null;
    }
}
