package com.star.sud.mapping.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POST")
public class Post extends AbstractEntity {

	private static final long serialVersionUID = 7289701572297329017L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_ID")
	private Integer postId;

	@Column(name = "POST_TYPE")
	private String postType;

	@Column(name = "POST_DESCRIPTION")
	private String postDescription;

	@Column(name = "IMAGE_NAME")
	private String imageName;

	@Lob
	@Column(name = "IMAGE", length = 2000)
	private String image;

	@OneToOne
	@JoinColumn(name = "POSTED_BY", referencedColumnName = "USER_ID")
	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comments> comments;

	@Column(name = "STATUS")
	private String status;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getPostDescription() {
		return postDescription;
	}

	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

}
