/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.gusuku.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.NoPersistentProperty;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.annotation.tiger.Sql;
import org.seasar.gusuku.entity.Comment;

@S2Dao(bean=Comment.class)
public interface CommentDao {
	
	@NoPersistentProperty("rdate")
	public void insert(Comment comment);
	@NoPersistentProperty("rdate")
	public void update(Comment comment);
	public void delete(Comment comment);
	
	/**
	 * 指定したreportidにひもづくCommentリストを取得します
	 * @param reportid
	 * @return
	 */
	@Query("Comment.REPORTID = /*reportid*/ AND Comment.DELFLAG = FALSE ORDER BY Comment.RDATE")
	public List<Comment> findByReportid(String reportid);

	/**
	 * 指定したIDにひもづくCommentを取得します
	 * @param cid
	 * @return
	 */
	@Query("Comment.ID = /*cid*/")
	public Comment findById(String cid);
	
	/**
	 * 指定したコメントの親コメント（登録日時が若い）を取得する
	 * @param reportid
	 * @param commentid
	 * @return
	 */
	@Query("Comment.REPORTID = /*reportid*/ AND Comment.RDATE < (SELECT RDATE FROM COMMENT WHERE ID = /*commentid*/) ORDER BY Comment.RDATE DESC LIMIT 1")
	@Arguments({"reportid","commentid"})
	public Comment findParentComment(String reportid, String commentid);
	
	@Sql("UPDATE COMMENT SET DELFLAG = TRUE WHERE ID = /*id*/")
	public void updateDelflag(String id);
}
